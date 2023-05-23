package com.baksara.app.ui.kelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.baksara.app.adapter.ListPelajaranAdapter
import com.baksara.app.database.Pelajaran
import com.baksara.app.databinding.ActivityKelasBinding
import com.baksara.app.helper.InitialDataSource

class KelasActivity : AppCompatActivity() {
    private var _binding: ActivityKelasBinding? = null
    private val binding get() = _binding!!
    private var modulId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        modulId = intent.getIntExtra(MODUL_ID, 0)
        val listPelajaran = getListPelajaranByModul(modulId)

        val layoutManager = LinearLayoutManager(this)
        binding.rvPelajaran.layoutManager = layoutManager

        val adapter = ListPelajaranAdapter(listPelajaran)
        binding.rvPelajaran.adapter = adapter
    }

    fun getListPelajaranByModul(modulId: Int): List<Pelajaran>{
        val listPelajaran: MutableList<Pelajaran> = mutableListOf()

        InitialDataSource.getPelajaran().forEach {pelajaran ->
            if (pelajaran.modulId == modulId){
                listPelajaran.add(pelajaran)
            }
        }

        return listPelajaran
    }

    companion object {
        const val MODUL_ID = "modul_id"
    }
}