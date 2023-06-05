package com.baksara.app.ui.kelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.R
import com.baksara.app.adapter.ListPelajaranAdapter
import com.baksara.app.database.Pelajaran
import com.baksara.app.databinding.ActivityKelasBinding
import com.baksara.app.helper.InitialDataSource

class KelasActivity : AppCompatActivity() {
    private var _binding: ActivityKelasBinding? = null
    private val binding get() = _binding!!
    private var modulId = 0
    private var modulNama : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modulId = intent.getIntExtra(MODUL_ID, 0)
        modulNama = intent.getStringExtra(MODUL_NAMA)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Modul " + modulNama

        _binding = ActivityKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPelajaran = getListPelajaranByModul(modulId)

        val layoutManager = LinearLayoutManager(this)
        binding.rvPelajaran.layoutManager = layoutManager

        val adapter = ListPelajaranAdapter(listPelajaran)
        binding.rvPelajaran.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    fun getListPelajaranByModul(modulId: Int): List<Pelajaran>{
        val listPelajaran: MutableList<Pelajaran> = mutableListOf()

        InitialDataSource.getPelajarans().forEach {pelajaran ->
            if (pelajaran.modulId == modulId){
                listPelajaran.add(pelajaran)
            }
        }

        return listPelajaran
    }

    companion object {
        const val MODUL_ID = "modul_id"
        const val MODUL_NAMA = "modul_nama"
    }
}