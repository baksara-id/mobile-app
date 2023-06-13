package com.baksara.app.ui.kelas

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.database.Pelajaran
import com.baksara.app.repository.BaksaraRepository
import kotlinx.coroutines.launch

class KelasViewModel(private val baksaraRepository: BaksaraRepository) : ViewModel() {
    fun getAllPelajaranByModul(modulId : Int): LiveData<List<Pelajaran>> = baksaraRepository.getAllPelajaransByModul(modulId)

}