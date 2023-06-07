package com.baksara.app.ui.artikel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ArtikelViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataArtikel: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataDetailArtikel: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()

    init {
        fetchAllArtikel()
    }

    fun fetchAllArtikel(){
        viewModelScope.launch {
            getAllArtikel().collect{ artikels->
                liveDataArtikel.value = artikels
            }
        }
    }

    fun fetchDetailArtikel(id: Int){
        viewModelScope.launch {
            getDetailArtikel(id).collect{ detailArtikel ->
                liveDataDetailArtikel.value = detailArtikel
            }
        }
    }

    suspend fun getAllArtikel(): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllArtikel()

    suspend fun getDetailArtikel(id:Int): Flow<Result<GraphQLResponse>> = baksaraRepository.getDetailArtikel(id)
}