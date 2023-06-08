package com.baksara.app.ui.tantangan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TantanganViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataTantangan: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataDetailTantangan: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataTantanganSudah: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataResponseSubmit: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataResponseUpdateUser: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()

    fun fetchAllTantanganUser(userId: Int){
        viewModelScope.launch {
            getAllTantanganUser(userId).collect{ tantangans->
                liveDataTantangan.value = tantangans
            }
        }
    }

    fun fetchDetailTantangan(id: Int){
        viewModelScope.launch {
            getDetailTantangan(id).collect{ tantangan ->
                liveDataDetailTantangan.value = tantangan
            }
        }
    }

    fun fetchRiwayatTantanganUser(userId: Int){
        viewModelScope.launch {
            getRiwayatTantangan(userId).collect{ riwayat->
                liveDataTantanganSudah.value = riwayat
            }
        }
    }

    fun fetchResponseSubmmit(userId: Int, tantanganId: Int, jawaban:String) {
        viewModelScope.launch {
            jawabTantangan(userId,tantanganId, jawaban).collect{ response ->

                response.onSuccess { submitResult ->
                    val isApproved = submitResult.data?.response?.is_approved?:false
                    if(isApproved){
                        updateUser().collect{ response ->
                            liveDataResponseUpdateUser.value = response
                        }
                    }
                }

                liveDataResponseSubmit.value = response
            }
        }
    }

    suspend fun getAllTantanganUser(userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllTantanganBelum(userId)

    suspend fun getDetailTantangan(id: Int) : Flow<Result<GraphQLResponse>> =
        baksaraRepository.getDetailTantangan(id)

    suspend fun getRiwayatTantangan(userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllTantanganSudah(userId)

    suspend fun jawabTantangan(userId: Int, tantanganId: Int, jawaban:String): Flow<Result<GraphQLResponse>> =
        baksaraRepository.submitJawabanTantangan(userId, tantanganId, jawaban)

    suspend fun updateUser(): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllLangganans()
}