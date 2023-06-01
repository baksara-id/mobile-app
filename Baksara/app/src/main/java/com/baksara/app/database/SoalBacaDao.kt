package com.baksara.app.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SoalBacaDao {
    @Query("SELECT * FROM soalbacas")
    fun getAllSoalBacas(): List<SoalBaca>
}