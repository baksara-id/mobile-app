package com.baksara.app.ui.scanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ScannerViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataResponseUpdateUser: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataResponseScanner: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()

    fun fetchUserResponse(newJumlahScan: Int, userId: Int){
        viewModelScope.launch {
            updateUserJumlahScan(newJumlahScan, userId).collect{ response ->
                liveDataResponseUpdateUser.value = response
            }
        }
    }

    suspend fun updateUserJumlahScan(newJumlahScan: Int, userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.updateUserScan(newJumlahScan, userId)
}