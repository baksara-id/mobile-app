package com.baksara.app.ui.soal.gambar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.database.SoalGambar
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import com.baksara.app.response.PredictResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File

class GambarViewModel(private val baksaraRepository: BaksaraRepository): ViewModel() {
    val liveDataPredict: MutableLiveData<Result<PredictResponse>> = MutableLiveData()

    fun getSoalGambarByPelajaran(pelajaranId : Int, urutan: Int): LiveData<SoalGambar> = baksaraRepository.getSoalGambarByPelajaran(pelajaranId, urutan)

    fun fetchPredictImage(img: File, actualClass: String){
        viewModelScope.launch {
            predictImage(img, actualClass).collect(){ result ->
                liveDataPredict.value = result
            }
        }
    }

    suspend fun predictImage(img: File, actualClass: String): Flow<Result<PredictResponse>> = baksaraRepository.predictResult(img, actualClass)
}