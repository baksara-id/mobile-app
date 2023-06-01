package com.baksara.app.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ModulDao {
    @Query("SELECT * FROM moduls")
    fun getAllModuls(): List<Modul>
}