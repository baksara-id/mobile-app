package com.baksara.app.ui.soal.pilihan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.SoalPilihan
import com.baksara.app.repository.BaksaraRepository

class PilihanViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    fun getSoalPilihanByPelajaran(pelajaranId : Int, urutan: Int): LiveData<SoalPilihan> = baksaraRepository.getSoalPilihanByPelajaran(pelajaranId, urutan)
}