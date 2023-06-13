package com.baksara.app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface BaksaraDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertModul(modul: List<Modul>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPelajaran(pelajaran: List<Pelajaran>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoalBaca(soalBaca: List<SoalBaca>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoalGambar(soalGambar: List<SoalGambar>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSoalPilihan(soalPilihan: List<SoalPilihan>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKamusBelajar(kamusBelajar: List<KamusBelajar>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKamus(kamus: List<Kamus>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPenggunaan(penggunaan: List<Penggunaan>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetModul(modul: List<Modul>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetPelajaran(pelajaran: List<Pelajaran>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetSoalBaca(soalBaca: List<SoalBaca>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetSoalGambar(soalGambar: List<SoalGambar>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetSoalPilihan(soalPilihan: List<SoalPilihan>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetKamusBelajar(kamusBelajar: List<KamusBelajar>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetKamus(kamus: List<Kamus>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun resetPenggunaan(penggunaan: List<Penggunaan>)

    @Query("SELECT * from modul")
    fun getAllModul(): LiveData<List<Modul>>

    @Query("UPDATE modul SET selesai = :selesai WHERE id = :modulId")
    suspend fun setModulSelesai(selesai: Boolean, modulId : Int)

    @Query("UPDATE modul SET terkunci = :terkunci WHERE id = :modulId")
    suspend fun setModulTerkunci(terkunci: Boolean, modulId : Int)

    @Query("UPDATE pelajaran SET selesai = :selesai WHERE id = :pelajaranId")
    suspend fun setPelajaranSelesai(selesai: Boolean, pelajaranId: Int)

    @Query("UPDATE pelajaran SET terkunci = :terkunci WHERE id = :pelajaranId")
    suspend fun setPelajaranTerkunci(terkunci: Boolean, pelajaranId : Int)

    @Query("SELECT * FROM pelajaran WHERE modulId = :modulId")
    fun getPelajaransByModul(modulId : Int): LiveData<List<Pelajaran>>


    @Query("SELECT * from SoalBaca WHERE pelajaranId = :pelajaranId AND urutan = :urutan")
    fun getSoalBacaByPelajaran(pelajaranId: Int, urutan: Int): LiveData<SoalBaca>

    @Query("SELECT * from soalgambar WHERE pelajaranId = :pelajaranId AND urutan = :urutan")
    fun getSoalGambarByPelajaran(pelajaranId: Int, urutan: Int): LiveData<SoalGambar>

    @Query("SELECT * from soalpilihan WHERE pelajaranId = :pelajaranId AND urutan = :urutan")
    fun getSoalPilihanByPelajaran(pelajaranId: Int, urutan: Int): LiveData<SoalPilihan>

    @Query("SELECT * from kamusbelajar")
    fun getAllKamusBelajar(): LiveData<List<KamusBelajar>>

    @Query("SELECT * from kamus WHERE kamusBelajarId = :belajarId")
    fun getAllKamusByKamusBelajar(belajarId: Int): LiveData<List<Kamus>>

    @Query("SELECT * from penggunaan WHERE kamusId = :kamusId")
    fun getAllPenggunaanByKamus(kamusId: Int): LiveData<List<Penggunaan>>


}