package com.baksara.app.ui.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.baksara.app.adapter.ListKamusDetailAdapter
import com.baksara.app.databinding.ActivityDetailKamusBinding
import com.baksara.app.helper.InitialDataSource

class DetailKamusActivity : AppCompatActivity() {
    private var _binding: ActivityDetailKamusBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListKamus()
    }

    private fun setupListKamus(){
        val layoutManager = GridLayoutManager(this, 5)
        binding.rvDetailKamus.layoutManager = layoutManager
        val adapter = ListKamusDetailAdapter(InitialDataSource.getAksaraKamus())
        binding.rvDetailKamus.adapter = adapter
    }

    companion object {
        const val KAMUSID = "kamus_id"
    }
}