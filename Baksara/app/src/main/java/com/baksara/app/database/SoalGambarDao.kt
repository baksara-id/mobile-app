package com.baksara.app.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SoalGambarDao {
    @Query("SELECT * FROM soalgambars")
    fun getAllSoalGambars(): List<SoalGambar>
}