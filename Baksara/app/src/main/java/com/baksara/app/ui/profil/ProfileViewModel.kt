package com.baksara.app.ui.profil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel (private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataLangganan: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()

    init {
        fetchLanggananData()
    }

    fun fetchLanggananData(){
        viewModelScope.launch {
            getAllLangganans().collect{ langganans->
                liveDataLangganan.value = langganans
            }
        }
    }

    suspend fun getAllLangganans(): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllLangganans()
}