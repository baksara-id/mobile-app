package com.baksara.app.ui.tantangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.database.Tantangan
import com.baksara.app.databinding.ActivitySoalTantanganBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.ui.tantangan.hasil.BerhasilTantanganActivity
import com.baksara.app.ui.tantangan.hasil.GagalTantanganActivity

class SoalTantanganActivity : AppCompatActivity() {
    private var _binding: ActivitySoalTantanganBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySoalTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTantanganData()
    }

    private fun setTantanganData(){
        val tantanganId = intent.getIntExtra(TANTANGAN_ID, 0)
        val tantangan = tantanganTerpilih(tantanganId)
        binding.tvXpSoalTantangan.text = "${tantangan.exp} XP"
        binding.tvDeskripsiSoalTantangan.text = tantangan.deskripsi
        binding.btnJawabTantangan.setOnClickListener {
            val intent: Intent
            if(binding.inputJawabanTantangan.text.toString().lowercase() == tantangan.kunci_jawaban.lowercase()){
                intent = Intent(this, BerhasilTantanganActivity::class.java)
            }else{
                intent = Intent(this, GagalTantanganActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    fun tantanganTerpilih(tantanganId: Int): Tantangan {
        return InitialDataSource.getTantangans().first{
            it.id == tantanganId
        }
    }

    companion object{
        const val TANTANGAN_ID = "tantangan_id"
    }
}