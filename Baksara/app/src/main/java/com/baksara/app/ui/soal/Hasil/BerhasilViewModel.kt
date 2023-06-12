package com.baksara.app.ui.soal.Hasil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BerhasilViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataUpdateRiwayatResponse : MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataResponseUpdateUserEXP: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataResponseUpdateUserLevelEXP: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataResponseLencana: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()

    fun fetchRiwayatBelajarResponse(userId: Int, pelajaranId: Int, modulId: Int){
        viewModelScope.launch {
            tambahRiwayatBelajar(userId, pelajaranId, modulId).collect{ response ->
                liveDataUpdateRiwayatResponse.value = response
            }
        }
    }

    fun fetchResponseUpdateUserExp(newEXP: Int, userId: Int){
        viewModelScope.launch {
            updateUserEXP(newEXP, userId).collect{ response ->
                liveDataResponseUpdateUserEXP.value = response
            }
        }
    }

    fun fetchResponseUpdateUserLevel(newLevel: Int, userId: Int, newEXP: Int){
        viewModelScope.launch {
            updateUserLevel(newLevel, userId, newEXP).collect{ response ->
                liveDataResponseUpdateUserLevelEXP.value = response
            }
        }
    }

    fun fetchLencana(userId: Int, modulId: Int){
        viewModelScope.launch {
            tambahLencana(userId,modulId).collect{ response ->
                liveDataResponseLencana.value = response
            }
        }
    }

    fun setPelajaranSelesai(selesai: Boolean, pelajaranId : Int){
        viewModelScope.launch {
            baksaraRepository.setPelajaranSelesai(selesai, pelajaranId)
        }
    }

    fun setPelajaranTerkunci(terkunci: Boolean, pelajaranId: Int){
        viewModelScope.launch {
            baksaraRepository.setPelajaranTerkunci(terkunci, pelajaranId)
        }
    }

    fun setModulSelesai(selesai: Boolean, pelajaranId : Int){
        viewModelScope.launch {
            baksaraRepository.setModulSelesai(selesai, pelajaranId)
        }
    }


    suspend fun tambahRiwayatBelajar(userId: Int, pelajaranId: Int, modulId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.tambahRiwayatBelajar(modulId,userId,pelajaranId)

    suspend fun updateUserEXP(newEXP: Int, userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.updateUserEXP(newEXP, userId)

    suspend fun updateUserLevel(newLevel: Int, userId: Int, newEXP: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.updateUserLevel(newLevel, userId, newEXP)

    suspend fun tambahLencana(userId: Int, modulId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.tambahLencana(modulId, userId)
}