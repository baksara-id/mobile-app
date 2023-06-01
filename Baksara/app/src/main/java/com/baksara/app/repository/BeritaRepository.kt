package com.baksara.app.repository

import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService

class BeritaRepository(
    private val userpref: UserPreferences,
    private val service: ApiService
) {

    suspend fun getAllBerita(){

    }

    companion object {
        @Volatile
        private var INSTANCE: BeritaRepository? = null

        fun getInstance(userpref: UserPreferences, apiService: ApiService): BeritaRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = BeritaRepository(userpref,apiService)
                }
                return INSTANCE as BeritaRepository
            }
        }
    }
}