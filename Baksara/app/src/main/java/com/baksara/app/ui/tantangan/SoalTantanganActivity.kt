package com.baksara.app.ui.tantangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivitySoalTantanganBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.response.Tantangan
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

        tantanganViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@SoalTantanganActivity))[TantanganViewModel::class.java]
        val tantanganId = intent.getIntExtra(TANTANGAN_ID, 0)
        tantanganId.let{
            tantanganViewModel.fetchDetailTantangan(it)
        }
        tantanganViewModel.liveDataDetailTantangan.observe(this){ result->
            result.onSuccess {
                val tantanganDetail = it.data?.detailTantangan ?: Tantangan(1,"",1,"","","","")
                setTantanganData(tantanganDetail)
            }
            result.onFailure {
                // Kalau gagal
            }
        }
    }

    private fun setTantanganData(tantangan: Tantangan){

        binding.tvXpSoalTantangan.text = "${tantangan.exp} XP"
        binding.tvDeskripsiSoalTantangan.text = tantangan.soal
        binding.tvLabelJawabanTantangan.text = tantangan.pertanyaan
        binding.btnJawabTantangan.setOnClickListener {
            val intent: Intent
            if(binding.inputJawabTantangan.text.toString().lowercase() == tantangan.kunci_jawaban?.lowercase()){
                intent = Intent(this, BerhasilTantanganActivity::class.java)
            }else{
                intent = Intent(this, GagalTantanganActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    companion object{
        const val TANTANGAN_ID = "tantangan_id"
    }
}