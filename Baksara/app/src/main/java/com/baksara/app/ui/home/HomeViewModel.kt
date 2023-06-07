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
    fun getAllModul(): LiveData<List<Modul>> = baksaraRepository.getAllModul()

    fun fetchAllTantanganUser(userId: Int){
        viewModelScope.launch {
            getAllTantanganUser(userId).collect{ tantangans->
                liveDataTantangan.value = tantangans
            }
        }
    }

    suspend fun getAllTantanganUser(userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllTantanganBelum(userId)
}
