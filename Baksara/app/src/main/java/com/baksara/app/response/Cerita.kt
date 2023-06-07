package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cerita (
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("judul")
    val judul: String?,
    @field: SerializedName("deskripsi")
    val deskripsi: String?,
    @field: SerializedName("url_isi")
    val url_isi: String?,
    @field: SerializedName("url_gambar")
    val url_gambar: String?
) : Parcelable
