package com.baksara.app

import android.content.Context
import com.baksara.app.database.BaksaraDatabase
import com.baksara.app.repository.BaksaraRepository

object Injection {
    fun provideRepository(context: Context): BaksaraRepository{
        val baksaraDatabase = BaksaraDatabase.getDatabase(context)
        return BaksaraRepository(baksaraDatabase.baksaraDao())
    }

}