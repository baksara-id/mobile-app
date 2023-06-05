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
import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService
import com.baksara.app.response.GraphQLRequest
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BaksaraRepository(
    private val baksaraDao: BaksaraDao,
    private val userpref: UserPreferences,
    private val service: ApiService
    ) {
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

    suspend fun register(email: String, name: String, password: String): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                             createUser(name: "$name", email: "$email", password: "$password") {
                                token
                              }
                        }
                    """.trimIndent()
                )
            )
            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    suspend fun login(email: String, password: String): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                            loginUser(email: "$email", password: "$password") {
                                id
                                name
                                email
                                token
                                avatar
                                kadaluwarsa
                                levels {
                                  id
                                  nama
                                }
                                lencanas {
                                  id
                                  nama
                                  url_gambar
                                }
                                langganan {
                                  id
                                  nama
                                  harga
                                  durasi
                                }
                            }
                        }
                    """.trimIndent()
                )
            )
            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }
}