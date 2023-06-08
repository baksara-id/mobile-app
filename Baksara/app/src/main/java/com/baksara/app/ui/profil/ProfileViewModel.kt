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
    val liveDataLencana: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataLaporan: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()

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
    fun fetchLencanaData(userId: Int){
        viewModelScope.launch {
            getAllLencanaUser(userId).collect{ lencanas->
                liveDataLencana.value = lencanas
            }
        }
    }

    fun fetchLaporanData(judul: String, isi: String, userId: Int){
        viewModelScope.launch {
            tambahLaporan(judul, isi, userId).collect{laporanResponse ->
                liveDataLaporan.value = laporanResponse
            }
        }
    }

    suspend fun getAllLangganans(): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllLangganans()

    suspend fun getAllLencanaUser(userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getUserLencanas(userId)

    suspend fun tambahLaporan(judul: String, isi: String, userId: Int): Flow<Result<GraphQLResponse>> =
        baksaraRepository.tambahLaporan(judul, isi, userId)
}