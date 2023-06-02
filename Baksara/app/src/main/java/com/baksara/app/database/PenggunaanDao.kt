package com.baksara.app.database

import androidx.room.Query

interface PenggunaanDao {
    @Query("SELECT * FROM penggunaans")
    fun getAllPenggunaans(): List<Penggunaan>
}