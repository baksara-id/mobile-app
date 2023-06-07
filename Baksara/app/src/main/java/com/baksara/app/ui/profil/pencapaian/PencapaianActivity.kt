package com.baksara.app.ui.profil.pencapaian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.R
import com.baksara.app.adapter.ListLencanaAdapter
import com.baksara.app.adapter.ListTantanganAdapter
import com.baksara.app.databinding.ActivityDetailCeritaBinding
import com.baksara.app.databinding.ActivityPencapaianBinding
import com.baksara.app.databinding.FragmentCeritaBinding
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.response.Tantangan
import com.baksara.app.ui.soal.pilihan.PilihanViewModel

class PencapaianActivity : AppCompatActivity() {
    private var _binding: ActivityPencapaianBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPencapaianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pencapaian"

        setupLencanaAdapter()
    }

    private fun setupLencanaAdapter(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLencana.layoutManager = layoutManager

        val adapter = ListLencanaAdapter(InitialDataSource.getLencanas())
        binding.rvLencana.adapter = adapter
    }
}