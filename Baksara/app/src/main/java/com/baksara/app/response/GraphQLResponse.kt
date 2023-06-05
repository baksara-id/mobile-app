package com.baksara.app.response

import com.google.gson.annotations.SerializedName

data class GraphQLResponse(
    @field:SerializedName("data")
    val data: Data?,

    @field:SerializedName("errors")
    val errors: List<Error>?,
)

data class Data(
    // Query
    @field:SerializedName("user")
    val user: User?,
    @field:SerializedName("tantanganBelum")
    val tantanganBelum: List<Tantangan>?,
    @field:SerializedName("detailTantangan")
    val detailTantangan: Tantangan?,
    @field:SerializedName("riwayatTantangan")
    val riwayatTantangan: List<Tantangan>?,
    @field:SerializedName("ceritas")
    val ceritas: List<Cerita>?,
    @field:SerializedName("detailCerita")
    val detailCerita: Cerita?,
    @field:SerializedName("artikels")
    val artikels: List<Artikel>?,
    @field:SerializedName("detailArtikel")
    val detailArtikel: Artikel?,
    @field:SerializedName("userLencana")
    val userLencana: List<Lencana>?,
    @field:SerializedName("userLangganans")
    val userLangganan: List<Langganan>?,
    // Mutation
    @field:SerializedName("createUser")
    val registerToken: String?,//token
    @field:SerializedName("loginUser")
    val loginUser: User?,//satu user
    @field:SerializedName("updateUser")
    val update: User?,//satu user
    @field:SerializedName("createRiwayatBelajar") // Kalau selesai pelajaran/level
    val riwayatBelajar: RiwayatBelajar?, // kalau selesai return riwayat belajarnya terus nanti cek modul dan pelajaran
    @field:SerializedName("createUserLencana") // Kalau selesai modul
    val lencana: Lencana?, // Kalau selesai return lencananya
    @field:SerializedName("createUserTantangan")// Kalau benar menjawab
    val is_approved: Boolean?, // Kalau benar return true
    @field:SerializedName("createLaporan") // Kalau tambah laporan
    val laporan: Laporan?, // Kalau benar return laporan
)

data class Error(
    @field:SerializedName("message")
    val message: String?,
)