package com.baksara.app.ui.scanner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.ActivityTransliterasiBinding
import com.baksara.app.ui.MainActivity
import com.baksara.app.utils.ToastUtils

class TransliterasiActivity : AppCompatActivity() {
    private var _binding: ActivityTransliterasiBinding? = null
    private val binding get() = _binding!!
    private lateinit var scannerViewModel: ScannerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTransliterasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setup Top Bar di Android
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Hasil Transliterasi"

        // Setup ViewModel
        scannerViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@TransliterasiActivity))[ScannerViewModel::class.java]

        // Ambil SharedPreferences
        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        // Ambil UserId
        val userId = userPref.getInt(MainActivity.UNIQUEID, -1)
        // Cek Langganan dari Data User di SharedPreferences
        val langganan = if(userPref.getInt(MainActivity.PREMIUM, 1) == 1) false else true
        // Update Jumlah Scan user dari data user di SharedPreferences (lgsg tambahkan satu)
        val jumlahScan = userPref.getInt(MainActivity.CURRENTLIMIT, 0) + 1
        // bikin editor untuk sharedpreferences
        val editor = userPref.edit()
        // Ambil status dari ScannerActivity
        val statusScan = intent.getStringExtra(STATUS) ?: "berhasil"
        // Ambil hasil dari ScannerActivity
        val hasilScan = intent.getStringExtra(HASIL)

        hasilScan.let{
            // Kalau status Scan tidak gagal tampilkan hasilnya kalau gagal toast scan gagal!
            if(statusScan != "gagal"){
                // Perbaruhi data di sharedPreferences jika berhasil scan
                editor.putInt(MainActivity.CURRENTLIMIT, jumlahScan)
                // Jika jumlahScannya berhasil dan bernilai >= 3 perbaruhi bahwa user sudah menghabiskan
                // Free Trial 3x Scan
                if(jumlahScan >= 3){
                    editor.putBoolean(MainActivity.LIMITREACH, true)
                }
                // Kalau user tidak berlangganan update database di CC
                if(!langganan){
                    scannerViewModel.fetchUserResponse(jumlahScan, userId)
                }
                // Buat Tampilan
                scannerViewModel.fetchTranslatorResult(it?:"")

                binding.tvAksaraLatinTransliterasi.text = "$hasilScan"
            }else
            {
                ToastUtils.showToast(this@TransliterasiActivity, "Scan Gagal Tolong ulangi lagi")
            }
        }
        // Simpan perubahan sharedPreferences
        editor.apply()

        scannerViewModel.liveDataResponseUpdateUser.observe(this){ result->
            result.onSuccess {
                if(it.data?.update?.jumlah_scan == 3) ToastUtils.showToast(this, "Jumlah Limit anda sudah mencapai 3 kali scan!")
                setUser(jumlahScan, userPref)
            }
            result.onFailure {

            }
        }

        scannerViewModel.liveDataTranslatorResponse.observe(this){ result->
            result.onSuccess {
                var hasilTranslate = ""
                it.hasil?.forEach {
                    hasilTranslate += it
                }
                binding.tvAksaraJawaTransliterasi.text = "$hasilTranslate"
            }
            result.onFailure {

            }
        }

        binding.btnDeteksiUlang.setOnClickListener {
            val intent = Intent(this@TransliterasiActivity, ScannerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    fun setUser(jumlahScan: Int, userPreferences: SharedPreferences){
        val editor = userPreferences.edit()
        editor.putInt(MainActivity.CURRENTLIMIT, jumlahScan)
        if(jumlahScan >= 3){
            editor.putBoolean(MainActivity.LIMITREACH, true)
        }
        else{
            editor.putBoolean(MainActivity.LIMITREACH, false)
        }
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        var HASIL = "HASILSCAN"
        var STATUS = "STATUSSCAN"
    }
}