package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tantangan (
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("nama")
    val nama: String?,
    @field: SerializedName("exp")
    val exp: Int?,
    @field: SerializedName("soal")
    val soal: String?,
    @field: SerializedName("pertanyaan")
    val pertanyaan: String?,
    @field: SerializedName("kunci_jawaban")
    val kunci_jawaban: String?,
    @field: SerializedName("url_gambar")
    val url_gambar: String?,
) : Parcelable