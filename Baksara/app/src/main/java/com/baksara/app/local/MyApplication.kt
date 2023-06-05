package com.baksara.app.local

import android.app.Application
import com.baksara.app.database.BaksaraDatabase
import com.baksara.app.repository.BaksaraRepository

class MyApplication: Application() {
    val database by lazy { BaksaraDatabase.getDatabase(this) }
    val repository by lazy { BaksaraRepository(database.baksaraDao())}
}