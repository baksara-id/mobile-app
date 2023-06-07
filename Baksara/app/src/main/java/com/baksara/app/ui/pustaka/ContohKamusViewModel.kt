package com.baksara.app.ui.pustaka

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.Penggunaan
import com.baksara.app.repository.BaksaraRepository

class ContohKamusViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    fun getAllPenggunaanAksaraByKamus(kamusId : Int): LiveData<List<Penggunaan>> = baksaraRepository.getAllPenggunaanByKamus(kamusId)
}