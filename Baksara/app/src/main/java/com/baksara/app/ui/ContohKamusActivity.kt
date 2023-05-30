package com.baksara.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baksara.app.databinding.ActivityContohKamusBinding

class ContohKamusActivity : AppCompatActivity() {
    private var _binding: ActivityContohKamusBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContohKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    companion object {
        const val AKSARAJAWA_ID = "aksara_id"
    }
}