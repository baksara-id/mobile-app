package com.baksara.app.ui.tantangan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.adapter.ListTantanganWideAdapter
import com.baksara.app.databinding.ActivityTantanganBinding
import com.baksara.app.helper.InitialDataSource

class TantanganActivity : AppCompatActivity() {

    private var _binding: ActivityTantanganBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTantanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTantanganAdapter()
    }

    private fun setupTantanganAdapter(){
        val layoutManager = LinearLayoutManager(this)
        binding.rvTantanganWide.layoutManager = layoutManager

        val adapter = ListTantanganWideAdapter(InitialDataSource.getTantangans())
        binding.rvTantanganWide.adapter = adapter
    }

}