package com.baksara.app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface BaksaraDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertModul(modul: List<Modul>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPelajaran(university: List<Pelajaran>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoalBaca(course: List<SoalBaca>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoalGambar(course: List<SoalGambar>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoalPilihan(course: List<SoalPilihan>)

    @Query("SELECT * from modul")
    fun getAllModul(): LiveData<List<Modul>>

    @Transaction
    @Query("SELECT * from modul")
    fun getAllModulAndPelajaran(): LiveData<List<ModulAndPelajaran>>

    @Query("SELECT * from pelajaran")
    fun getAllPelajaranAndSoalBaca(): LiveData<List<Pelajaran>>

}