package com.baksara.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.databinding.ActivityDetailKamusBinding

class DetailKamusActivity : AppCompatActivity() {
    private var _binding: ActivityDetailKamusBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val KAMUSID = "kamus_id"
    }
}