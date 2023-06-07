package com.baksara.app

import android.content.Context
import com.baksara.app.database.BaksaraDatabase
import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiConfig
import com.baksara.app.network.ApiService
import com.baksara.app.repository.BaksaraRepository

object Injection {
    fun provideRepository(context: Context): BaksaraRepository{
        val baksaraDatabase = BaksaraDatabase.getDatabase(context)
        val service: ApiService = ApiConfig.getApiService()
        return BaksaraRepository(baksaraDatabase.baksaraDao(), service)
    }

}