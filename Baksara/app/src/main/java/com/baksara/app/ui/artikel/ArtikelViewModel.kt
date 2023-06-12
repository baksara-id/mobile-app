package com.baksara.app.ui.artikel

import android.util.Log
import androidx.lifecycle.LiveData
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
    private val _liveDataIsLoading:MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsLoading: LiveData<Boolean> = _liveDataIsLoading

    fun fetchAllArtikel(){
        _liveDataIsLoading.value = true
        viewModelScope.launch {
            getAllArtikel().collect{ artikels->
                liveDataArtikel.value = artikels
                _liveDataIsLoading.value = false
            }
        }
    }

    fun fetchDetailArtikel(id: Int){
        _liveDataIsLoading.value = true
        viewModelScope.launch {
            getDetailArtikel(id).collect{ detailArtikel ->
                liveDataDetailArtikel.value = detailArtikel
                Log.d("a",detailArtikel.toString())
            }
        }
    }

    suspend fun getAllArtikel(): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllArtikel()

    suspend fun getDetailArtikel(id:Int): Flow<Result<GraphQLResponse>> = baksaraRepository.getDetailArtikel(id)
}