package com.baksara.app.ui.profil.pencapaian

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.adapter.ListLencanaAdapter
import com.baksara.app.databinding.ActivityPencapaianBinding
import com.baksara.app.databinding.FragmentCeritaBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.response.*
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.profil.ProfileViewModel
import com.baksara.app.ui.soal.pilihan.PilihanViewModel

class PencapaianActivity : AppCompatActivity() {
    private var _binding: ActivityPencapaianBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPencapaianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pencapaian"

        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@PencapaianActivity))[ProfileViewModel::class.java]
        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userLogin = getUser(userPref)
        userLogin.let {
            profileViewModel.fetchLencanaData(it.id?: -1)
        }

        profileViewModel.liveDataLencana.observe(this){result ->
            result.onSuccess {
                val listLencana = it.data?.userLencana ?: emptyList()
                setupLencanaAdapter(listLencana)
            }
            result.onFailure {
                // Kalau gagal
            }
        }

        if(userLogin.langganan?.id != 1){
            // User Premium
            binding.cvBadgeUserPencapaian.backgroundTintList = ContextCompat.getColorStateList(this@PencapaianActivity, R.color.light_premium)
            binding.badgeUserPencapaian.text = "User Premium"
            binding.badgeUserPencapaian.setBackgroundResource(R.drawable.bg_border_premium)
            binding.badgeUserPencapaian.setTextColor(ContextCompat.getColor(this@PencapaianActivity, R.color.premium))
        }
        else{
            // User Standard
            binding.cvBadgeUserPencapaian.backgroundTintList = ContextCompat.getColorStateList(this@PencapaianActivity, R.color.light_standard)
            binding.badgeUserPencapaian.text = "User Standard"
            binding.badgeUserPencapaian.setBackgroundResource(R.drawable.bg_border_standard)
            binding.badgeUserPencapaian.setTextColor(ContextCompat.getColor(this@PencapaianActivity, R.color.neutral_300))
        }

        setupAkun(userLogin)
    }

    private fun setupLencanaAdapter(listLencana : List<Lencana>){
        val layoutManager = GridLayoutManager(this, 4)
        binding.rvLencana.layoutManager = layoutManager

        val adapter = ListLencanaAdapter(listLencana)
        binding.rvLencana.adapter = adapter
    }

    fun setupAkun(user:User){
        binding.tvCurrentLevel.text = user.level.toString()
        val currentEXP = user.exp ?: 0
        binding.tvCurrentAccountExp.text = currentEXP.toString()
        val maxEXP = user.level?.times(500) ?: 500
        binding.tvMaxAccountExp.text = maxEXP.toString()
        binding.expBar.max = maxEXP
        binding.expBar.progress = currentEXP
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