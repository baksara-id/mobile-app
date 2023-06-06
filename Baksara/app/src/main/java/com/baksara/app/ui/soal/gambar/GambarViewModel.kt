package com.baksara.app.ui.soal.gambar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.SoalGambar
import com.baksara.app.repository.BaksaraRepository

class GambarViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    fun getSoalGambarByPelajaran(pelajaranId : Int, urutan: Int): LiveData<SoalGambar> = baksaraRepository.getSoalGambarByPelajaran(pelajaranId, urutan)
}