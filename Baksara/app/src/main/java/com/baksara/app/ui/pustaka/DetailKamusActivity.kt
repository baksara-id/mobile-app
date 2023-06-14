package com.baksara.app.ui.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.adapter.ListKamusDetailAdapter
import com.baksara.app.database.Kamus
import com.baksara.app.databinding.ActivityDetailKamusBinding

class DetailKamusActivity : AppCompatActivity() {
    private var _binding: ActivityDetailKamusBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailKamusViewModel: DetailKamusViewModel
    private var kamusBelajarId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kamus"

        kamusBelajarId = intent.getIntExtra(KAMUSID, 0)

        binding.tvJudulKamus.text = intent.getStringExtra(KAMUSJUDUL)
        binding.tvDeskripsiKamus.text = intent.getStringExtra(KAMUSDESKRIPSI)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        detailKamusViewModel = ViewModelProvider(this, viewModelFactory)[DetailKamusViewModel::class.java]

        detailKamusViewModel.getAllKamusByKamusBelajar(kamusBelajarId).observe(this){
            if(it.isNotEmpty()){
                setupListKamus(it)
            }else{

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    private fun setupListKamus(listKamus: List<Kamus>){
        val layoutManager = GridLayoutManager(this, 5)
        binding.rvDetailKamus.layoutManager = layoutManager
        val adapter = ListKamusDetailAdapter(listKamus)
        binding.rvDetailKamus.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KAMUSID = "kamus_id"
        const val KAMUSJUDUL = "kamus_judul"
        const val KAMUSDESKRIPSI = "kamus_deskripsi"
    }
}