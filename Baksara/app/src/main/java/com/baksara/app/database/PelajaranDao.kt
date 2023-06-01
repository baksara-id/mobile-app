package com.baksara.app.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PelajaranDao {
    @Query("SELECT * FROM pelajarans")
    fun getAllPelajarans(): List<Pelajaran>
}