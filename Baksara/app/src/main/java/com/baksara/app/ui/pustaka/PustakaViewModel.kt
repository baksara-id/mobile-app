package com.baksara.app.ui.pustaka

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import com.baksara.app.response.TranslatorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PustakaViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataCerita: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataDetailCerita: MutableLiveData<Result<GraphQLResponse>> = MutableLiveData()
    val liveDataTranslatorResponse: MutableLiveData<Result<TranslatorResponse>> = MutableLiveData()
    private val _liveDataIsLoading:MutableLiveData<Boolean> = MutableLiveData()
    val liveDataIsLoading: LiveData<Boolean> = _liveDataIsLoading

    fun fetchAllCerita(){
        _liveDataIsLoading.value = true
        viewModelScope.launch {
            getAllCerita().collect{ ceritas->
                liveDataCerita.value = ceritas
                _liveDataIsLoading.value = false
            }
        }
    }

    fun fetchDetailCerita(id: Int){
        _liveDataIsLoading.value = true
        viewModelScope.launch {
            getDetailCerita(id).collect{detailCerita ->
                liveDataDetailCerita.value = detailCerita
                _liveDataIsLoading.value = false
            }
        }
    }

    fun fetchTranslatorResult(text: String){
        viewModelScope.launch {
            getTranslator(text).collect{ response ->
                liveDataTranslatorResponse.value = response
            }
        }
    }

    suspend fun getAllCerita(): Flow<Result<GraphQLResponse>> =
        baksaraRepository.getAllCerita()

    suspend fun getDetailCerita(id:Int): Flow<Result<GraphQLResponse>> = baksaraRepository.getDetailCerita(id)

    suspend fun getTranslator(text:String): Flow<Result<TranslatorResponse>> =
        baksaraRepository.translator(text)
}