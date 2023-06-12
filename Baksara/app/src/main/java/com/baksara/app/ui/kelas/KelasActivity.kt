package com.baksara.app.ui.kelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.ViewModelFactory
import com.baksara.app.adapter.ListPelajaranAdapter
import com.baksara.app.database.Pelajaran
import com.baksara.app.databinding.ActivityKelasBinding
import com.baksara.app.helper.InitialDataSource

class KelasActivity : AppCompatActivity() {
    private var _binding: ActivityKelasBinding? = null
    private val binding get() = _binding!!
    private var modulId = 0
    private lateinit var kelasViewModel: KelasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modulId = intent.getIntExtra(MODUL_ID, 0)
        modulNama = intent.getStringExtra(MODUL_NAMA)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Modul " + modulNama

        _binding = ActivityKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        kelasViewModel = ViewModelProvider(this, viewModelFactory)[KelasViewModel::class.java]

    }

    override fun onResume() {
        super.onResume()

        kelasViewModel.getAllPelajaranByModul(modulId).observe(this){pelajarans->
            if(pelajarans.isNotEmpty()){
                val layoutManager = LinearLayoutManager(this)
                binding.rvPelajaran.layoutManager = layoutManager

                val adapter = ListPelajaranAdapter(pelajarans, modulId)
                binding.rvPelajaran.adapter = adapter
            }else{

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }

    companion object {
        const val MODUL_ID = "modul_id"
        const val MODUL_NAMA = "modul_nama"
        var modulNama : String? = ""
    }
}