package com.baksara.app.ui.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityDetailCeritaBinding
import com.bumptech.glide.Glide

class DetailCeritaActivity : AppCompatActivity() {
    private var _binding: ActivityDetailCeritaBinding? = null
    private val binding get() = _binding!!
    private lateinit var ceritaViewModel: PustakaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailCeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ceritaViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@DetailCeritaActivity))[PustakaViewModel::class.java]
        val ceritaId = intent.getIntExtra(CERITA_ID, -1)

        ceritaViewModel.liveDataDetailCerita.observe(this){result->
            result.onSuccess { detailCerita->
                Glide.with(this)
                    .load(detailCerita.data?.detailCerita?.url_gambar)
                    .placeholder(R.drawable.arjunadummy2)
                    .fitCenter()
                    .into(binding.ivPlaceholder);
                Glide.with(this)
                    .load(detailCerita.data?.detailCerita?.url_isi)
                    .placeholder(R.drawable.template_cerita)
                    .fitCenter()
                    .into(binding.contentCerita);
            }
            result.onFailure {
                // Kalau Gagal
            }
        }

        ceritaId.let {
            ceritaViewModel.fetchDetailCerita(it)
        }
    }

    companion object {
        const val CERITA_ID = "cerita_id"
    }
}