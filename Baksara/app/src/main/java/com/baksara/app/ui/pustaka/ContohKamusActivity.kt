package com.baksara.app.ui.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.adapter.ListKamusContohAdapter
import com.baksara.app.database.Penggunaan
import com.baksara.app.databinding.ActivityContohKamusBinding

class ContohKamusActivity : AppCompatActivity() {
    private var _binding: ActivityContohKamusBinding? = null
    private val binding get() = _binding!!
    private lateinit var contohKamusViewModel: ContohKamusViewModel
    private var aksaraId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContohKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Kamus"

        aksaraId = intent.getIntExtra(AKSARAJAWA_ID, 0)
        binding.tvDisplayAksaraText.text = intent.getStringExtra(JAWA_AKSARA)
        binding.tvDisplayLatinText.text = intent.getStringExtra(JAWA_LATIN)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        contohKamusViewModel = ViewModelProvider(this, viewModelFactory)[ContohKamusViewModel::class.java]

        contohKamusViewModel.getAllPenggunaanAksaraByKamus(aksaraId).observe(this){ penggunaans ->
            if(penggunaans.isNotEmpty()){
                setupPenggunaanAdapter(penggunaans)
            }else{

            }
        }
    }

    private fun setupPenggunaanAdapter(penggunaans: List<Penggunaan>){
        val layoutManager = LinearLayoutManager(this)
        binding.rvPenggunaan.layoutManager = layoutManager

        val adapter = ListKamusContohAdapter(penggunaans)
        binding.rvPenggunaan.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val AKSARAJAWA_ID = "aksara_id"
        const val JAWA_AKSARA = "jawa_aksara"
        const val JAWA_LATIN = "jawa_latin"
    }
}