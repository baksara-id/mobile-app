package com.baksara.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baksara.app.database.Modul
import com.baksara.app.database.ModulAndPelajaran
import com.baksara.app.database.Pelajaran
import com.baksara.app.database.PelajaranAndSoalBaca
import com.baksara.app.database.PelajaranAndSoalGambar
import com.baksara.app.database.PelajaranAndSoalPilihan
import com.baksara.app.repository.BaksaraRepository
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val baksaraRepository: BaksaraRepository) : ViewModel() {
    init {
        insertAllData()
    }

    private fun insertAllData() = viewModelScope.launch {
        baksaraRepository.insertAllData()
    }

    suspend fun register(email: String, name: String, password: String): Flow<Result<GraphQLResponse>> =
        baksaraRepository.register(email, name, password)

    suspend fun login(email: String, password: String): Flow<Result<GraphQLResponse>> =
        baksaraRepository.login(email,password)
}