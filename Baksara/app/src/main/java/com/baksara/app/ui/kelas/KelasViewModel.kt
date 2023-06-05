package com.baksara.app.ui.kelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.Pelajaran
import com.baksara.app.repository.BaksaraRepository

class KelasViewModel(private val baksaraRepository: BaksaraRepository) : ViewModel() {
    fun getAllPelajaranByModul(modulId : Int): LiveData<List<Pelajaran>> = baksaraRepository.getAllPelajaransByModul(modulId)
}