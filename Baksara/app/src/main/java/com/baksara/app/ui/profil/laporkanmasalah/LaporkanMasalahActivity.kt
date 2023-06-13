package com.baksara.app.ui.profil.laporkanmasalah

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityLanggananBinding
import com.baksara.app.databinding.ActivityLaporkanMasalahBinding
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.profil.ProfileViewModel

class LaporkanMasalahActivity : AppCompatActivity() {
    private var _binding: ActivityLaporkanMasalahBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLaporkanMasalahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Laporkan Masalah"

        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@LaporkanMasalahActivity))[ProfileViewModel::class.java]
        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userLogin = getUser(userPref)

        binding.btnKirimLaporan.setOnClickListener {
            val judul = binding.inputJudulLaporan.text
            val isi = binding.inputIsiLaporan.text
            profileViewModel.fetchLaporanData(judul.toString(), isi.toString(), userLogin.id?:-1)
        }

        profileViewModel.liveDataLaporan.observe(this){ response->
            response.onSuccess {
                showDialogLaporSukses(this)
            }
            response.onFailure {
                showDialogLaporGagal(this)
            }
        }
    }

    private fun showDialogLaporGagal(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_danger_information)
        textTitle.text = "Laporan Gagal Terkirim"
        textDesc.text = "Terdapat kesalahan pada sistem"
        buttonInformation.text = "Mengerti"
        buttonInformation.background.setTint(resources.getColor(R.color.danger))

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDialogLaporSukses(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        imgInformation.setImageResource(R.drawable.img_logo_confirmation)
        textTitle.text = "Laporan Terkirim"
        textDesc.text = "Laporan anda telah terkirim ke tim baksara. Permasalahan anda akan segera kami atasi."
        buttonInformation.text = "Mengerti"
        buttonInformation.background.setTint(resources.getColor(R.color.success))

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            binding.inputJudulLaporan.setText("")
            binding.inputJudulLaporan.setText("")
            dialog.dismiss()
        }

        dialog.show()
    }

    fun getUser(userPref: SharedPreferences): User {
        val name = userPref.getString(MainActivity.FULLNAME,"")
        val email = userPref.getString(MainActivity.EMAIL,"")
        val avatar = userPref.getString(MainActivity.AVATAR,"")
        val id = userPref.getInt(MainActivity.UNIQUEID,0)
        val exp = userPref.getInt(MainActivity.EXP,0)
        val level = userPref.getInt(MainActivity.LEVEL,0)
        val limit = userPref.getInt(MainActivity.CURRENTLIMIT,0)
        val kelas = userPref.getInt(MainActivity.KELAS,0)
        val modul = userPref.getInt(MainActivity.MODUL,0)
        val token = userPref.getString(MainActivity.TOKEN,"")
        val langganan = userPref.getInt(MainActivity.PREMIUM,1)
        val _langgananObject = Langganan(langganan,"",0.0f,0)
        var listOfRiwayat = mutableListOf<RiwayatBelajar>()
        val _riwayatBelajarObject = RiwayatBelajar(0,id,modul,kelas)
        listOfRiwayat.add(_riwayatBelajarObject)
        return User(id,_langgananObject,name,email,token,avatar, exp,level,limit,kadaluarsa = null,null,listOfRiwayat)
    }
}