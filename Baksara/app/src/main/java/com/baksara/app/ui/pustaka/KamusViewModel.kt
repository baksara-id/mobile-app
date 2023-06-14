package com.baksara.app.ui.pustaka

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.database.KamusBelajar
import com.baksara.app.repository.BaksaraRepository
import kotlinx.coroutines.launch

class KamusViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    fun getAllKamusBelajar(): LiveData<List<KamusBelajar>> = baksaraRepository.getAllKamusBelajar()

    fun syncKamusTerkunci(terkunci: Boolean){
        viewModelScope.launch {
            baksaraRepository.setKamusTerkunci(terkunci)
        }
    }


}