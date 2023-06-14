package com.baksara.app.utils

import android.content.Context
import com.baksara.app.database.BaksaraDatabase
import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiConfig
import com.baksara.app.network.ApiMLConfig
import com.baksara.app.network.ApiService
import com.baksara.app.repository.BaksaraRepository

object Injection {
    fun provideRepository(context: Context): BaksaraRepository{
        val baksaraDatabase = BaksaraDatabase.getDatabase(context)
        val service: ApiService = ApiConfig.getApiService()
        val mlservice: ApiService = ApiMLConfig.getApiService()
        return BaksaraRepository(baksaraDatabase.baksaraDao(), service, mlservice)
    }

}