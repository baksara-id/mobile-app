package com.baksara.app.ui.artikel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityDetailArtikelBinding
import com.baksara.app.ui.pustaka.DetailCeritaActivity
import com.bumptech.glide.Glide

class DetailArtikelActivity : AppCompatActivity() {
    private var _binding: ActivityDetailArtikelBinding? = null
    private val binding get() = _binding!!
    private lateinit var artikelViewModel: ArtikelViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artikelViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this@DetailArtikelActivity))[ArtikelViewModel::class.java]
        val artikelID = intent.getIntExtra(ARTIKELID, -1)

        artikelViewModel.liveDataDetailArtikel.observe(this){ result->
            result.onSuccess { detailArtikel ->
                binding.tvJudulDetailArtikel.text = detailArtikel.data?.detailArtikel?.judul
                binding.tvDeskripsiDetailArtikel.text = detailArtikel.data?.detailArtikel?.isi
                binding.tvTanggalDetailArtikel.text = detailArtikel.data?.detailArtikel?.kategori?.nama
                Glide.with(this)
                    .load(detailArtikel.data?.detailArtikel?.url_gambar)
                    .placeholder(R.drawable.arjunadummy2)
                    .fitCenter()
                    .into(binding.ivPlaceholder2);
            }
            result.onFailure {
                // Kalau error
            }
        }
    }

    companion object {
        const val ARTIKELID = "artikelID"
    }
}