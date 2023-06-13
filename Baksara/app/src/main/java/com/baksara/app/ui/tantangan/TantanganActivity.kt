package com.baksara.app.ui.tantangan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.ViewModelFactory
import com.baksara.app.adapter.ListTantanganWideAdapter
import com.baksara.app.databinding.ActivityTantanganBinding
import com.baksara.app.response.Langganan
import com.baksara.app.response.RiwayatBelajar
import com.baksara.app.response.Tantangan
import com.baksara.app.response.User
import com.baksara.app.ui.MainActivity
import java.util.Locale

class TantanganActivity : AppCompatActivity() {

    private var _binding: ActivityTantanganBinding? = null
    private val binding get() = _binding!!
    private lateinit var tantanganViewModel: TantanganViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tantangan"

        tantanganViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@TantanganActivity))[TantanganViewModel::class.java]

        val userPref = getSharedPreferences(MainActivity.PREF, Context.MODE_PRIVATE)
        val userLogin = getUser(userPref)

        userLogin.let{
            tantanganViewModel.fetchAllTantanganUser(it.id?:-1)
        }
        tantanganViewModel.liveDataTantangan.observe(this){ result ->
            result.onSuccess { tantangans->
                val listTantanganBelum = tantangans.data?.tantanganBelum ?: emptyList()
                if(listTantanganBelum.isEmpty()){
                    binding.rvTantanganWide.visibility = View.GONE
                    binding.tvTantanganBelumKosong.visibility = View.VISIBLE
                }
                else{
                    binding.rvTantanganWide.visibility = View.VISIBLE
                    binding.tvTantanganBelumKosong.visibility = View.GONE
                }
                setupTantanganAdapter(listTantanganBelum)
            }

            result.onFailure {
                //Kalau gagal
            }
        }

        binding.btnTantanganSelengkapnya3.setOnClickListener {
            val intent = Intent(this, RiwayatTantanganActivity::class.java)
            startActivity(intent)
        }

        tantanganViewModel.liveDataIsLoading.observe(this){
            showLoading(it)
        }
    }

    private fun setupTantanganAdapter(listTantanganBelum: List<Tantangan>){
        val layoutManager = LinearLayoutManager(this)
        binding.rvTantanganWide.layoutManager = layoutManager

        val adapter = ListTantanganWideAdapter(listTantanganBelum)
        binding.rvTantanganWide.adapter = adapter
        binding.inputTantanganSearch.addTextChangedListener (object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filteredList = listTantanganBelum.filter {
                    it.nama?.lowercase()?.contains(query) == true
                }
                adapter.setListTantangan(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    fun showLoading(isLoading: Boolean){
        binding.tantanganLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
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