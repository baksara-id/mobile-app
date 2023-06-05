package com.baksara.app.repository

import androidx.lifecycle.LiveData
import com.baksara.app.database.BaksaraDao
import com.baksara.app.database.Kamus
import com.baksara.app.database.KamusAndPenggunaan
import com.baksara.app.database.Modul
import com.baksara.app.database.ModulAndPelajaran
import com.baksara.app.database.Pelajaran
import com.baksara.app.database.PelajaranAndSoalBaca
import com.baksara.app.database.PelajaranAndSoalGambar
import com.baksara.app.database.PelajaranAndSoalPilihan
import com.baksara.app.helper.InitialDataSource

class BaksaraRepository(private val baksaraDao: BaksaraDao) {
    fun getAllModul():LiveData<List<Modul>> = baksaraDao.getAllModul()
    fun getAllModulAndPelajaran():LiveData<List<ModulAndPelajaran>> =baksaraDao.getAllModulAndPelajaran()
    fun getAllPelajaran(): LiveData<List<Pelajaran>> = baksaraDao.getAllPelajaran()
    fun getAllPelajaranAndSoalBaca():LiveData<List<PelajaranAndSoalBaca>> = baksaraDao.getAllPelajaranAndSoalBaca()
    fun getAllPelajaranAndSoalGambar():LiveData<List<PelajaranAndSoalGambar>> = baksaraDao.getAllPelajaranAndSoalGambar()
    fun getAllPelajaranAndSoalPilihan():LiveData<List<PelajaranAndSoalPilihan>> = baksaraDao.getAllPelajaranAndSoalPilihan()
    fun getAllKamus():LiveData<List<Kamus>> = baksaraDao.getAllKamus()
    fun getAllKamusAndPenggunaan():LiveData<List<KamusAndPenggunaan>> = baksaraDao.getAllKamusAndPenggunaan()

    suspend fun insertAllData(){
        baksaraDao.insertModul(InitialDataSource.getModuls())
        baksaraDao.insertPelajaran(InitialDataSource.getPelajarans())
        baksaraDao.insertSoalBaca(InitialDataSource.getSoalBacas())
        baksaraDao.insertSoalGambar(InitialDataSource.getSoalGambars())
        baksaraDao.insertSoalPilihan(InitialDataSource.getSoalPilihans())
        baksaraDao.insertKamus(InitialDataSource.getAksaraKamus())
        baksaraDao.insertPenggunaan(InitialDataSource.getPenggunaanKamus())
    }
}