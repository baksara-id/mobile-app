package com.baksara.app.repository

import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService

class KelasRepository(
    private val userpref: UserPreferences,
    private val service: ApiService
) {

    suspend fun updateLevel(){

    }

    suspend fun selesaiKelas(){

    }

    suspend fun selesaiModul(){

    }

    suspend fun cekAkurasiGambar(){

    }

    suspend fun dapatBadge(){

    }

    suspend fun selesaiTantangan(){

    }

    suspend fun getAllTantangan(){

    }

    companion object {
        @Volatile
        private var INSTANCE: KelasRepository? = null

        fun getInstance(userpref: UserPreferences, apiService: ApiService): KelasRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = KelasRepository(userpref,apiService)
                }
                return INSTANCE as KelasRepository
            }
        }
    }
}