package com.baksara.app.repository

import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService

class PustakaRepository(
    private val userpref: UserPreferences,
    private val service: ApiService
) {

    suspend fun getAllCerita(){

    }

    suspend fun translateAksara(){

    }

    companion object {
        @Volatile
        private var INSTANCE: PustakaRepository? = null

        fun getInstance(userpref: UserPreferences, apiService: ApiService): PustakaRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = PustakaRepository(userpref,apiService)
                }
                return INSTANCE as PustakaRepository
            }
        }
    }
}