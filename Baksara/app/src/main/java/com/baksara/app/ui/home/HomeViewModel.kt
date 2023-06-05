package com.baksara.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.baksara.app.database.Modul
import com.baksara.app.repository.BaksaraRepository

class HomeViewModel(private val baksaraRepository: BaksaraRepository) : ViewModel() {
    fun getAllModul(): LiveData<List<Modul>> = baksaraRepository.getAllModul()
}
