package com.baksara.app.ui.soal.baca

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.SoalBaca
import com.baksara.app.repository.BaksaraRepository

class BacaViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    fun getSoalBacaByPelajaran(pelajaranId : Int, urutan: Int): LiveData<SoalBaca> = baksaraRepository.getSoalBacaByPelajaran(pelajaranId, urutan)
}