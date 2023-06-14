package com.baksara.app.ui.scanner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Hasil Transliterasi"

        scannerViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@TransliterasiActivity))[ScannerViewModel::class.java]

        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userId = userPref.getInt(MainActivity.UNIQUEID, -1)
        val langganan = if(userPref.getInt(MainActivity.PREMIUM, 1) == 1) false else true
        val jumlahScan = userPref.getInt(MainActivity.CURRENTLIMIT, 0) + 1
        val editor = userPref.edit()
        val statusScan = intent.getStringExtra(STATUS) ?: "berhasil"
        val hasilScan = intent.getStringExtra(HASIL)

        hasilScan.let{
            scannerViewModel.fetchTranslatorResult(it?:"")
        }

        editor.putInt(MainActivity.CURRENTLIMIT, jumlahScan)
        if(jumlahScan >= 3){
            editor.putBoolean(MainActivity.LIMITREACH, true)
        }
        editor.apply()

        if(!langganan){
            scannerViewModel.fetchUserResponse(jumlahScan, userId)
        }

        scannerViewModel.liveDataResponseUpdateUser.observe(this){ result->
            result.onSuccess {
                if(it.data?.update?.jumlah_scan == 3)
                ToastUtils.showToast(this, "Jumlah Limit anda sudah mencapai 3 kali scan!")
                setUser(jumlahScan, userPref)
            }
            result.onFailure {

            }
        }

        scannerViewModel.liveDataTranslatorResponse.observe(this){ result->
            result.onSuccess {
                val hasilTranslate = it.hasil
                binding.tvAksaraJawaTransliterasi.text = "$hasilTranslate"
            }
            result.onFailure {

            }
        }
        binding.tvAksaraLatinTransliterasi.text = "$hasilScan"

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