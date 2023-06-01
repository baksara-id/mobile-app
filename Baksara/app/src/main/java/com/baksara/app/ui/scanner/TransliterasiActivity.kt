package com.baksara.app.ui.scanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.databinding.ActivityTransliterasiBinding

class TransliterasiActivity : AppCompatActivity() {
    private var _binding: ActivityTransliterasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTransliterasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAksaraJawaTransliterasi.text = "ꦲꦫꦶꦆꦤꦶ"
        binding.tvAksaraLatinTransliterasi.text = "Hari Ini"
    }
}