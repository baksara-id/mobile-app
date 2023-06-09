package com.baksara.app.ui.pustaka

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

    init {
        fetchAllCerita()
    }

    fun fetchAllCerita(){
        viewModelScope.launch {
            getAllCerita().collect{ ceritas->
                liveDataCerita.value = ceritas
            }
        }
    }

    fun fetchDetailCerita(id: Int){
        viewModelScope.launch {
            getDetailCerita(id).collect{detailCerita ->
                liveDataDetailCerita.value = detailCerita
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