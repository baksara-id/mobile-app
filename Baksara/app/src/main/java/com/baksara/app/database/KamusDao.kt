package com.baksara.app.database

import androidx.room.Query

interface KamusDao {
    @Query("SELECT * FROM kamuses")
    fun getAllKamuses(): List<Kamus>
}