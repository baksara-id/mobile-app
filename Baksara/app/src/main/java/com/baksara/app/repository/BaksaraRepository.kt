package com.baksara.app.repository

import androidx.lifecycle.LiveData
import com.baksara.app.database.BaksaraDao
import com.baksara.app.database.Kamus
import com.baksara.app.database.KamusBelajar
import com.baksara.app.database.Modul
import com.baksara.app.database.Pelajaran
import com.baksara.app.database.Penggunaan
import com.baksara.app.database.SoalBaca
import com.baksara.app.database.SoalGambar
import com.baksara.app.database.SoalPilihan
import com.baksara.app.helper.InitialDataSource
import com.baksara.app.network.ApiService
import com.baksara.app.response.*
import com.baksara.app.response.GraphQLRequest
import com.baksara.app.response.GraphQLResponse
import com.baksara.app.response.PredictResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class BaksaraRepository(
    private val baksaraDao: BaksaraDao,
    private val service: ApiService,
    private val mlservice: ApiService
    ) {
    fun getAllModul():LiveData<List<Modul>> = baksaraDao.getAllModul()
    fun getAllPelajaransByModul(modulId: Int): LiveData<List<Pelajaran>> = baksaraDao.getPelajaransByModul(modulId)

    suspend fun setModulSelesai(selesai: Boolean, modulId: Int) = baksaraDao.setModulSelesai(selesai, modulId)
    suspend fun setModulTerkunci(terkunci: Boolean, modulId: Int) = baksaraDao.setModulSelesai(terkunci, modulId)

    suspend fun setPelajaranSelesai(selesai: Boolean, pelajaranId: Int) = baksaraDao.setPelajaranSelesai(selesai, pelajaranId)
    suspend fun setPelajaranTerkunci(terkunci: Boolean, pelajaranId: Int) = baksaraDao.setPelajaranTerkunci(terkunci, pelajaranId)

    fun getSoalBacaByPelajaran(pelajaranId: Int, urutan: Int):LiveData<SoalBaca> = baksaraDao.getSoalBacaByPelajaran(pelajaranId, urutan)
    fun getSoalGambarByPelajaran(pelajaranId: Int, urutan: Int):LiveData<SoalGambar> = baksaraDao.getSoalGambarByPelajaran(pelajaranId, urutan)
    fun getSoalPilihanByPelajaran(pelajaranId: Int, urutan: Int):LiveData<SoalPilihan> = baksaraDao.getSoalPilihanByPelajaran(pelajaranId, urutan)

    fun getAllKamusBelajar():LiveData<List<KamusBelajar>> = baksaraDao.getAllKamusBelajar()

    fun getAllKamusByKamusBelajar(belajarId: Int):LiveData<List<Kamus>> = baksaraDao.getAllKamusByKamusBelajar(belajarId)

    fun getAllPenggunaanByKamus(kamusId: Int):LiveData<List<Penggunaan>> = baksaraDao.getAllPenggunaanByKamus(kamusId)


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

    suspend fun submitJawabanTantangan(userId: Int, tantanganId: Int, jawaban: String): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          createUserTantangan(user_id: $userId, tantangan_id: $tantanganId, jawaban: "$jawaban") {
                            is_approved
                            jawaban
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

    suspend fun tambahLaporan(judul: String, isi: String, userId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          createLaporan(user_id: $userId, judul: "$judul", isi: "$isi") {
                            id
                            isi
                            judul
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

    suspend fun tambahRiwayatBelajar(modulId: Int, userId: Int, pelajaranId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          createRiwayatBelajar(user_id: $userId, nomor_modul: $modulId, nomor_pelajaran: $pelajaranId) {
                            id
                            nomor_modul
                            nomor_pelajaran
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

    suspend fun tambahLencana(modulId: Int, userId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          createUserLencana(user_id: $userId, lencana_id: $modulId) {
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

    suspend fun updateUserEXP(newEXP: Int, userId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          updateUser(id: $userId, exp: $newEXP) {
                            name
                            level
                            id
                            exp
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

    suspend fun updateUserLevel(newLevel: Int, userId: Int, newEXP: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          updateUser(id: $userId, level: $newLevel, exp: $newEXP) {
                            name
                            level
                            id
                            exp
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

    suspend fun updateUserScan(newJumlahScan: Int, userId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          updateUser(id: $userId, jumlah_scan: $newJumlahScan) {
                            name
                            id
                            jumlah_scan
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

    suspend fun updateUserLangganan(langgananId: Int, userId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                          updateUser(id: $userId, langganan_id: $langgananId) {
                            name
                            id
                            langganan {
                              durasi
                              id
                              harga
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

//    MACHINE LEARNING API
    suspend fun translator(text: String): Flow<Result<TranslatorResponse>> = flow {
        try {
            val response = mlservice.translator(
                TranslatorRequest(text)
            )
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun predictResult(
        img: File,
        aksaraClass : String
    ): Flow<Result<PredictResponse>> = flow{
        try {
            val requestImageFile = img.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData("img", img.name, requestImageFile)
            val actualClass : RequestBody = aksaraClass.toRequestBody("text/plain".toMediaType())
            val response = mlservice.predict(imageMultipart, actualClass)

            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    suspend fun scannerResult(
        img: File,
    ): Flow<Result<ScannerResponse>> = flow{
        try {
            val requestImageFile = img.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData("img", img.name, requestImageFile)
            val response = mlservice.scanner(imageMultipart)

            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

//=========================================================QUERY============================================================
    suspend fun getAllCerita(): Flow<Result<GraphQLResponse>> = flow {
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
    }.catch{ e->
    e.printStackTrace()
    emit(Result.failure(e))
}

    suspend fun getDetailCerita(id: Int): Flow<Result<GraphQLResponse>> = flow {
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
    }.catch{ e->
        e.printStackTrace()
        emit(Result.failure(e))
    }

    suspend fun getAllArtikel(): Flow<Result<GraphQLResponse>> = flow {
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
    }.catch{ e->
        e.printStackTrace()
        emit(Result.failure(e))
    }

    suspend fun getDetailArtikel(id: Int): Flow<Result<GraphQLResponse>> = flow {
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
    }.catch{ e->
        e.printStackTrace()
        emit(Result.failure(e))
    }

    suspend fun getAllTantanganBelum(idUser: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                          getTantangans(user_id: $idUser) {
                            id
                            nama
                            exp
                            soal
                            pertanyaan
                            kunci_jawaban
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

    suspend fun getDetailTantangan(id: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                          tantangan(id: $id) {
                            id
                            nama
                            exp
                            soal
                            pertanyaan
                            kunci_jawaban
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

    suspend fun getAllTantanganSudah(idUser: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                          riwayat_tantangans(user_id: $idUser) {
                            id
                            nama
                            exp
                            soal
                            pertanyaan
                            kunci_jawaban
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

    suspend fun getAllLangganans(): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                            langganans {
                            durasi
                            harga
                            id
                            nama
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

    suspend fun getUserLencanas(userId: Int): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        query {
                          user_lencanas(user_id: $userId) {
                            id
                            nama
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