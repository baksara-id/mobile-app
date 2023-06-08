package com.baksara.app.ui.tantangan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivitySoalTantanganBinding
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.Tantangan
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import com.baksara.app.ui.tantangan.hasil.BerhasilTantanganActivity
import com.baksara.app.ui.tantangan.hasil.GagalTantanganActivity

class SoalTantanganActivity : AppCompatActivity() {
    private var _binding: ActivitySoalTantanganBinding? = null
    private val binding get() = _binding!!
    private lateinit var tantanganViewModel: TantanganViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySoalTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Soal Tantangan"

        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userLogin = getUser(userPref)

        tantanganViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@SoalTantanganActivity))[TantanganViewModel::class.java]
        val tantanganId = intent.getIntExtra(TANTANGAN_ID, 0)
        tantanganId.let{
            tantanganViewModel.fetchDetailTantangan(it)
        }
        tantanganViewModel.liveDataDetailTantangan.observe(this){ result->
            result.onSuccess {
                val tantanganDetail = it.data?.detailTantangan ?: Tantangan(1,"",1,"","","","")
                setTantanganData(tantanganDetail, userLogin.id?: -1)
            }
            result.onFailure {
                // Kalau gagal
            }
        }
    }

    private fun setTantanganData(tantangan: Tantangan, userId: Int){
        binding.tvXpSoalTantangan.text = "${tantangan.exp} XP"
        binding.tvDeskripsiSoalTantangan.text = tantangan.soal
        binding.tvLabelJawabanTantangan.text = tantangan.pertanyaan
        binding.tvJudulSoalTantangan.text = tantangan.nama
        binding.tvXpSoalTantangan.text = tantangan.exp.toString()
        binding.btnJawabTantangan.setOnClickListener {
            tantanganViewModel.fetchResponseSubmmit(userId, tantangan.id ?: -1, binding.inputJawabTantangan.text.toString())
            tantanganViewModel.liveDataResponseSubmit.observe(this){ result->
                result.onSuccess {
                    val intent: Intent
                    val is_approved = it.data?.response?.is_approved ?: false
                    if(is_approved){
                        intent = Intent(this, BerhasilTantanganActivity::class.java)
                    }else{
                        intent = Intent(this, GagalTantanganActivity::class.java)
                    }
                    startActivity(intent)
                    finish()
                }
                result.onFailure {
                    // Kalau internet gagal atau apapun
                }
            }
            if(binding.inputJawabTantangan.text.toString().lowercase() == tantangan.kunci_jawaban?.lowercase()){
                intent = Intent(this, BerhasilTantanganActivity::class.java)
            }else{
                intent = Intent(this, GagalTantanganActivity::class.java)
            }
        }
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
        val langganan = userPref.getInt(MainActivity.PREMIUM,0)
        val _langgananObject = Langganan(langganan,"",0.0f,0)
        var listOfRiwayat = mutableListOf<RiwayatBelajar>()
        val _riwayatBelajarObject = RiwayatBelajar(0,id,modul,kelas)
        listOfRiwayat.add(_riwayatBelajarObject)
        return User(id,_langgananObject,name,email,token,avatar, exp,level,limit,kadaluarsa = null,null,listOfRiwayat)
    }

    companion object{
        const val TANTANGAN_ID = "tantangan_id"
    }
}