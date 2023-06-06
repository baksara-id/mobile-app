package com.baksara.app.ui.pustaka

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.Kamus
import com.baksara.app.repository.BaksaraRepository

class DetailKamusViewModel(private val baksaraRepository: BaksaraRepository): ViewModel()  {
    fun getAllKamusByKamusBelajar(kamusBelajarId : Int): LiveData<List<Kamus>> = baksaraRepository.getAllKamus(kamusBelajarId)
}