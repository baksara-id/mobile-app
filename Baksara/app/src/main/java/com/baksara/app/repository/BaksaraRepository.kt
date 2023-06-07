package com.baksara.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baksara.app.database.BaksaraDao
import com.baksara.app.database.Kamus
import com.baksara.app.database.KamusAndPenggunaan
import com.baksara.app.database.KamusBelajar
import com.baksara.app.database.Modul
import com.baksara.app.database.ModulAndPelajaran
import com.baksara.app.database.Pelajaran
import com.baksara.app.database.PelajaranAndSoalGambar
import com.baksara.app.database.PelajaranAndSoalPilihan
import com.baksara.app.database.SoalBaca
import com.baksara.app.database.SoalGambar
import com.baksara.app.database.SoalPilihan
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService
import com.baksara.app.response.GraphQLRequest
import com.baksara.app.response.GraphQLResponse
import com.baksara.app.response.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BaksaraRepository(
    private val baksaraDao: BaksaraDao,
    private val service: ApiService
    ) {
    fun getAllModul():LiveData<List<Modul>> = baksaraDao.getAllModul()
    fun getAllModulAndPelajaran():LiveData<List<ModulAndPelajaran>> =baksaraDao.getAllModulAndPelajaran()
    fun getAllPelajaransByModul(modulId: Int): LiveData<List<Pelajaran>> = baksaraDao.getPelajaransByModul(modulId)
    fun getSoalBacaByPelajaran(pelajaranId: Int, urutan: Int):LiveData<SoalBaca> = baksaraDao.getSoalBacaByPelajaran(pelajaranId, urutan)
    fun getSoalGambarByPelajaran(pelajaranId: Int, urutan: Int):LiveData<SoalGambar> = baksaraDao.getSoalGambarByPelajaran(pelajaranId, urutan)
    fun getSoalPilihanByPelajaran(pelajaranId: Int, urutan: Int):LiveData<SoalPilihan> = baksaraDao.getSoalPilihanByPelajaran(pelajaranId, urutan)

    fun getAllPelajaranAndSoalGambar():LiveData<List<PelajaranAndSoalGambar>> = baksaraDao.getAllPelajaranAndSoalGambar()
    fun getAllPelajaranAndSoalPilihan():LiveData<List<PelajaranAndSoalPilihan>> = baksaraDao.getAllPelajaranAndSoalPilihan()
    fun getAllKamusBelajar():LiveData<List<KamusBelajar>> = baksaraDao.getAllKamusBelajar()

    fun getAllKamus(belajarId: Int):LiveData<List<Kamus>> = baksaraDao.getAllKamusByKamusBelajar(belajarId)
    fun getAllKamusAndPenggunaan():LiveData<List<KamusAndPenggunaan>> = baksaraDao.getAllKamusAndPenggunaan()

    suspend fun insertAllData(){
        baksaraDao.insertModul(InitialDataSource.getModuls())
        baksaraDao.insertPelajaran(InitialDataSource.getPelajarans())
        baksaraDao.insertSoalBaca(InitialDataSource.getSoalBacas())
        baksaraDao.insertSoalGambar(InitialDataSource.getSoalGambars())
        baksaraDao.insertSoalPilihan(InitialDataSource.getSoalPilihans())
        baksaraDao.insertKamusBelajar(InitialDataSource.getListKamus())
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
                                exp
                                level
                                jumlah_scan
                                kadaluwarsa
                                riwayat_belajars {
                                  nomor_modul
                                  nomor_pelajaran
                                  id
                                }
                                lencanas {
                                  id
                                  url_gambar
                                }
                                langganan {
                                  id
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

    suspend fun getAllCerita(): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query Ceritas {
                          ceritas {
                            id
                            judul
                            url_gambar
                            url_isi
                            deskripsi
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

    suspend fun getDetailCerita(id: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                          cerita(id: $id) {
                            deskripsi
                            id
                            judul
                            url_gambar
                            url_isi
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

    suspend fun getAllArtikel(): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query Artikels {
                          artikels {
                            id
                            isi
                            judul
                            url_gambar
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

    suspend fun getDetailArtikel(id: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                          artikel(id: $id) {
                            id
                            isi
                            judul
                            url_gambar
                            createdAt
                            kategori {
                              id
                              nama
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

    suspend fun getAllTantanganBelum(): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query Artikels {
                          artikels {
                            id
                            isi
                            judul
                            url_gambar
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