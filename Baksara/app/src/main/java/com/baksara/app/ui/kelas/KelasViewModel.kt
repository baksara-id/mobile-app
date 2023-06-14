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
    fun syncPelajaranSelesai(selesai: Boolean, lastPelajaranId : Int, lastModulId: Int){
        //membuka nomor pelajaran sebelumnya
        val nomorPelajaranBefore = (lastModulId - 1)*4
        for (pelajaranId in 1 until lastPelajaranId + nomorPelajaranBefore){
            viewModelScope.launch {
                baksaraRepository.setPelajaranSelesai(selesai, pelajaranId)
            }
        }
    }

    fun syncPelajaranTerkunci(terkunci: Boolean, lastPelajaranId : Int, lastModulId: Int){
        //membuka nomor pelajaran sebelumnya
        val nomorPelajaranBefore = (lastModulId - 1)*4
        viewModelScope.launch {
            for (pelajaranId in 1 .. lastPelajaranId + nomorPelajaranBefore){
                baksaraRepository.setPelajaranTerkunci(terkunci, pelajaranId)
            }
        }
    }
}