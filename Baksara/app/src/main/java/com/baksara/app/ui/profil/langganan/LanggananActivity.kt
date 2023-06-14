package com.baksara.app.ui.profil.langganan

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
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.ActivityLanggananBinding
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.profil.ProfileViewModel
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class LanggananActivity : AppCompatActivity() {
    private var _binding: ActivityLanggananBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel
    private var currentSelected: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLanggananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Langganan"

        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@LanggananActivity))[ProfileViewModel::class.java]
        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userLogin = getUser(userPref)

        if(userLogin.langganan?.id != 1){
            // User Premium
            binding.cvBadgeUserLangganan.backgroundTintList = ContextCompat.getColorStateList(this, R.color.light_premium)
            binding.badgeUserPencapaianLangganan.text = "User Premium"
            binding.badgeUserPencapaianLangganan.setBackgroundResource(R.drawable.bg_border_premium)
            binding.badgeUserPencapaianLangganan.setTextColor(ContextCompat.getColor(this, R.color.premium))
        }
        else{
            // User Standard
            binding.cvBadgeUserLangganan.backgroundTintList = ContextCompat.getColorStateList(this, R.color.light_standard)
            binding.badgeUserPencapaianLangganan.text = "User Standard"
            binding.badgeUserPencapaianLangganan.setBackgroundResource(R.drawable.bg_border_standard)
            binding.badgeUserPencapaianLangganan.setTextColor(ContextCompat.getColor(this, R.color.neutral_300))
        }

        Glide.with(this)
            .load(userLogin.avatar)
            .placeholder(R.drawable.arjunadummy2)
            .centerCrop()
            .into(binding.imgProfilePencapaianLangganan);

        profileViewModel.liveDataLangganan.observe(this){ result->
            result.onSuccess {
                val listLangganan = it.data?.userLangganan ?: emptyList()
                setCardLangganan(listLangganan)
            }
            result.onFailure {
                // Kalau gagal
            }

        }
        resetBackground()
        binding.cvPaketLangganan1.setOnClickListener {
            resetBackground()
            binding.layout1.setBackgroundResource(R.drawable.bg_border_main300)
            currentSelected = 2
        }
        binding.cvPaketLangganan2.setOnClickListener {
            resetBackground()
            binding.layout2.setBackgroundResource(R.drawable.bg_border_main300)
            currentSelected = 3
        }
        binding.cvPaketLangganan3.setOnClickListener {
            resetBackground()
            binding.layout3.setBackgroundResource(R.drawable.bg_border_main300)
            currentSelected = 4
        }
        binding.cvPaketLangganan4.setOnClickListener {
            resetBackground()
            binding.layout4.setBackgroundResource(R.drawable.bg_border_main300)
            currentSelected = 5
        }

        binding.btnBeli.setOnClickListener {
            showDialogBeli(this, currentSelected, userLogin, userPref)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    private fun showDialogBeli(context: Context, selected: Int?, userLogin: User, userPref: SharedPreferences) {
        val builder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.item_dialog_information, null)


        val imgInformation: ImageView = dialogView.findViewById(R.id.img_information)
        val textTitle: TextView = dialogView.findViewById(R.id.tv_information_title)
        val textDesc: TextView = dialogView.findViewById(R.id.tv_information_description)
        val buttonInformation: Button = dialogView.findViewById(R.id.btn_information)

        if(selected == null){
            imgInformation.setImageResource(R.drawable.img_logo_danger_information)
            textTitle.text = "Pembelian Gagal"
            textDesc.text = "Tolong untuk memilih paket dari user premium yang hendak dibeli!"
            buttonInformation.background.setTint(resources.getColor(R.color.danger))
        }else{
            imgInformation.setImageResource(R.drawable.img_logo_confirmation)
            textTitle.text = "Pembelian Berhasil"
            textDesc.text = "Selamat anda telah menjadi user premium. Hak akses fitur premium telah diberikan."
            buttonInformation.background.setTint(resources.getColor(R.color.success))
            profileViewModel.fetchUpdateLanggananResponse(selected, userLogin.id ?: -1)
            // User Premium
            binding.cvBadgeUserLangganan.backgroundTintList = ContextCompat.getColorStateList(this, R.color.light_premium)
            binding.badgeUserPencapaianLangganan.text = "User Premium"
            binding.badgeUserPencapaianLangganan.setBackgroundResource(R.drawable.bg_border_premium)
            binding.badgeUserPencapaianLangganan.setTextColor(ContextCompat.getColor(this, R.color.premium))
            setUser(userPref)
        }
        buttonInformation.text = "Mengerti"

        builder.setView(dialogView)
        val dialog: AlertDialog = builder.create()
        buttonInformation.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun resetBackground(){
        binding.layout1.background = null
        binding.layout2.background = null
        binding.layout3.background = null
        binding.layout4.background = null
    }

    fun setCardLangganan(listLangganan: List<Langganan>){
        binding.tvDurasiLangganan1.text = listLangganan[1].durasi.toString() + " Bulan"
        binding.tvDurasiLangganan2.text = listLangganan[2].durasi.toString() + " Bulan"
        binding.tvDurasiLangganan3.text = listLangganan[3].durasi.toString() + " Bulan"
        binding.tvDurasiLangganan4.text = listLangganan[4].durasi.toString() + " Bulan"
        binding.tvHarga1.text = rupiahformatter(listLangganan[1].harga?: 0.0f)
        binding.tvHarga2.text = rupiahformatter(listLangganan[2].harga?: 0.0f)
        binding.tvHarga3.text = rupiahformatter(listLangganan[3].harga?: 0.0f)
        binding.tvHarga4.text = rupiahformatter(listLangganan[4].harga ?: 0.0f)
    }

    fun rupiahformatter(harga: Float): String{
        val localeId = Locale("id", "ID")
        val currency = Currency.getInstance("IDR")
        val numberFormat = NumberFormat.getCurrencyInstance(localeId)
        numberFormat.currency = currency
        return numberFormat.format(harga)
    }

    fun setUser(userPref: SharedPreferences){
        val editor = userPref.edit()
        editor.putInt(MainActivity.PREMIUM, currentSelected?:1)
        editor.apply()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}