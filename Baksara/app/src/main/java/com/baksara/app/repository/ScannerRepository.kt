package com.baksara.app.repository

import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService

class ScannerRepository(
    private val userpref: UserPreferences,
    private val service: ApiService
) {

    suspend fun transliterasi(){

    }

    companion object {
        @Volatile
        private var INSTANCE: ScannerRepository? = null

        fun getInstance(userpref: UserPreferences, apiService: ApiService): ScannerRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = ScannerRepository(userpref,apiService)
                }
                return INSTANCE as ScannerRepository
            }
        }
    }
}