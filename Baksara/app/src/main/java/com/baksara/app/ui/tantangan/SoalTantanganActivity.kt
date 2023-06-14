package com.baksara.app.ui.tantangan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.utils.ViewModelFactory
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

        hideVisibility()

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
                showVisibility()
            }
            result.onFailure {
                // Kalau gagal
                hideVisibility()
            }
        }
    }

    private fun setTantanganData(tantangan: Tantangan, userId: Int){
        binding.tvXpSoalTantangan.text = "${tantangan.exp} XP"
        binding.tvDeskripsiSoalTantangan.text = tantangan.soal
        binding.tvLabelJawabanTantangan.text = tantangan.pertanyaan
        binding.tvJudulSoalTantangan.text = tantangan.nama
        binding.btnJawabTantangan.setOnClickListener {
            tantanganViewModel.fetchResponseSubmmit(userId, tantangan.id ?: -1, binding.inputJawabTantangan.text.toString())
            tantanganViewModel.liveDataResponseSubmit.observe(this){ result->
                result.onSuccess {
                    val intent: Intent
                    val is_approved = it.data?.response?.is_approved ?: false
                    if(is_approved){
                        intent = Intent(this, BerhasilTantanganActivity::class.java)
                        intent.putExtra(BerhasilTantanganActivity.USERID, userId)
                        intent.putExtra(BerhasilTantanganActivity.EXPEARNED, tantangan.exp)
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
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    fun hideVisibility(){
        binding.cvJawabTantangan.visibility = View.GONE
        binding.cvSoalTantangan.visibility = View.GONE
        binding.cvJudulTantangan.visibility = View.GONE
        binding.tvLabelJawabanTantangan.visibility = View.GONE
        binding.tvLabelSoalTantangan.visibility = View.GONE
        binding.btnJawabTantangan.visibility = View.GONE
        binding.progressBarSoalTantangan.visibility = View.VISIBLE
    }

    fun showVisibility(){
        binding.cvJawabTantangan.visibility = View.VISIBLE
        binding.cvSoalTantangan.visibility = View.VISIBLE
        binding.cvJudulTantangan.visibility = View.VISIBLE
        binding.tvLabelJawabanTantangan.visibility = View.VISIBLE
        binding.tvLabelSoalTantangan.visibility = View.VISIBLE
        binding.btnJawabTantangan.visibility = View.VISIBLE
        binding.progressBarSoalTantangan.visibility = View.GONE
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

    companion object{
        const val TANTANGAN_ID = "tantangan_id"
    }
}