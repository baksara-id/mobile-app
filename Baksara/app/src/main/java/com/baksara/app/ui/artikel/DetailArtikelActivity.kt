package com.baksara.app.ui.artikel

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.baksara.app.R
import com.baksara.app.ViewModelFactory
import com.baksara.app.databinding.ActivityDetailArtikelBinding
import com.baksara.app.ui.pustaka.DetailCeritaActivity
import com.bumptech.glide.Glide
import java.util.Date
import java.util.Locale

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

        artikelID.let {
            artikelViewModel.fetchDetailArtikel(it)
        }

        artikelViewModel.liveDataDetailArtikel.observe(this){ result->
            result.onSuccess { detailArtikel ->
                binding.tvJudulDetailArtikel.text = detailArtikel.data?.detailArtikel?.judul
                binding.tvDeskripsiDetailArtikel.text = detailArtikel.data?.detailArtikel?.isi
                binding.tvTanggalDetailArtikel.text = detailArtikel.data?.detailArtikel?.kategori?.nama
                val dummyData = "2023-06-01 13:19:44"
                val databaseFormat = "yyyy-MM-dd HH:mm:ss"
                val artikelFormat = "EEEE, dd/MM/yyyy HH:mm:ss"

                val sdfOriginal = SimpleDateFormat(databaseFormat, Locale.getDefault())
                val sdfTarget = SimpleDateFormat(artikelFormat, Locale.getDefault())

                val date: Date = sdfOriginal.parse(dummyData)
                val convertedDateTime: String = sdfTarget.format(date)
                binding.tvTanggal.text = convertedDateTime

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