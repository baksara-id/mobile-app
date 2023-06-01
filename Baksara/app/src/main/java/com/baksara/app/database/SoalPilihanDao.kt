package com.baksara.app.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SoalPilihanDao {
    @Query("SELECT * FROM soalpilihans")
    fun getAllSoalPilihans(): List<SoalPilihan>
}