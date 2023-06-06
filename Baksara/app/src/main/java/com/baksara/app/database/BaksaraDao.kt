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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKamusBelajar(course: List<KamusBelajar>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKamus(course: List<Kamus>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPenggunaan(course: List<Penggunaan>)

    @Query("SELECT * from modul")
    fun getAllModul(): LiveData<List<Modul>>

    @Transaction
    @Query("SELECT * from modul")
    fun getAllModulAndPelajaran(): LiveData<List<ModulAndPelajaran>>

    @Query("SELECT * FROM pelajaran WHERE modulId = :modulId")
    fun getPelajaransByModul(modulId : Int): LiveData<List<Pelajaran>>

    @Query("SELECT * from SoalBaca WHERE pelajaranId = :pelajaranId AND id = :urutan")
    fun getSoalBacaByPelajaran(pelajaranId: Int, urutan: Int): LiveData<SoalBaca>

    @Query("SELECT * from soalgambar WHERE pelajaranId = :pelajaranId AND id = :urutan")
    fun getSoalGambarByPelajaran(pelajaranId: Int, urutan: Int): LiveData<SoalGambar>

    @Query("SELECT * from soalpilihan WHERE pelajaranId = :pelajaranId AND id = :urutan")
    fun getSoalPilihanByPelajaran(pelajaranId: Int, urutan: Int): LiveData<SoalPilihan>

    @Transaction
    @Query("SELECT * from pelajaran")
    fun getAllPelajaranAndSoalGambar(): LiveData<List<PelajaranAndSoalGambar>>

    @Transaction
    @Query("SELECT * from pelajaran")
    fun getAllPelajaranAndSoalPilihan(): LiveData<List<PelajaranAndSoalPilihan>>

    @Query("SELECT * from kamusbelajar")
    fun getAllKamusBelajar(): LiveData<List<KamusBelajar>>

    @Query("SELECT * from kamus WHERE kamusBelajarId = :belajarId")
    fun getAllKamusByKamusBelajar(belajarId: Int): LiveData<List<Kamus>>

    @Transaction
    @Query("SELECT * from kamus")
    fun getAllKamusAndPenggunaan(): LiveData<List<KamusAndPenggunaan>>


}