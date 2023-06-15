package com.baksara.app.ui.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.utils.ViewModelFactory
import com.baksara.app.databinding.ActivityDetailCeritaBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop

class DetailCeritaActivity : AppCompatActivity() {
    private var _binding: ActivityDetailCeritaBinding? = null
    private val binding get() = _binding!!
    private lateinit var ceritaViewModel: PustakaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailCeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Cerita"

        hideVisibility()
        ceritaViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@DetailCeritaActivity))[PustakaViewModel::class.java]
        val ceritaId = intent.getIntExtra(CERITA_ID, -1)
        ceritaId.let {
            ceritaViewModel.fetchDetailCerita(it)
        }
        ceritaViewModel.liveDataDetailCerita.observe(this){result->
            result.onSuccess { detailCerita->
                Glide.with(this)
                    .load(detailCerita.data?.detailCerita?.url_gambar)
                    .placeholder(R.drawable.arjunadummy2)
                    .fitCenter()
                    .into(binding.ivPlaceholder)
                Glide.with(this)
                    .load(detailCerita.data?.detailCerita?.url_isi)
                    .into(binding.contentCerita)
                showVisibility()
            }
            result.onFailure {
                // Kalau Gagal
                hideVisibility()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    fun hideVisibility(){
        binding.contentCerita.visibility = View.GONE
        binding.view5.visibility = View.GONE
        binding.ivPlaceholder.visibility = View.GONE
        binding.loadingBarDetailCerita.visibility = View.VISIBLE
    }

    fun showVisibility(){
        binding.contentCerita.visibility = View.VISIBLE
        binding.view5.visibility = View.VISIBLE
        binding.ivPlaceholder.visibility = View.VISIBLE
        binding.loadingBarDetailCerita.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val CERITA_ID = "cerita_id"
    }
}