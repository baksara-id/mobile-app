package com.baksara.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.database.Modul
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val baksaraRepository: BaksaraRepository) : ViewModel() {
    val liveDataTantangan: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    private val _liveDataIsLoading:MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsLoading: LiveData<Boolean> = _liveDataIsLoading
    fun getAllModul(): LiveData<List<Modul>> = baksaraRepository.getAllModul()

    fun fetchAllTantanganUser(userId: Int){
        _liveDataIsLoading.value = true
        viewModelScope.launch {
            getAllTantanganUser(userId).collect{ tantangans->
                liveDataTantangan.value = tantangans
                _liveDataIsLoading.value = false
            }
        }
    }
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

    fun syncModulTerkunci(terkunci: Boolean){
        viewModelScope.launch {
            baksaraRepository.setModulTerkunci(terkunci)
        }
    }

    fun syncModulSelesai(selesai: Boolean, lastModulId : Int){
        viewModelScope.launch {
            for (modulId in 1 until lastModulId){
                baksaraRepository.setModulSelesai(selesai, modulId)
            }
        }
    }
    suspend fun getAllTantanganUser(userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllTantanganBelum(userId)
}
