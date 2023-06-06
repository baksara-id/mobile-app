package com.baksara.app.ui.pustaka

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.KamusBelajar
import com.baksara.app.repository.BaksaraRepository

class KamusViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    fun getAllKamusBelajar(): LiveData<List<KamusBelajar>> = baksaraRepository.getAllKamusBelajar()
}