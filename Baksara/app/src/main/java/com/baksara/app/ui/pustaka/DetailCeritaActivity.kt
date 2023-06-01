package com.baksara.app.ui.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.databinding.ActivityDetailCeritaBinding

class DetailCeritaActivity : AppCompatActivity() {
    private var _binding: ActivityDetailCeritaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailCeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val CERITA_ID = "cerita_id"
    }
}