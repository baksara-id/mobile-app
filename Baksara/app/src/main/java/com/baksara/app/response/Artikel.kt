package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artikel (
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("kategori_id")
    val kategori_id: Int?,
    @field: SerializedName("judul")
    val judul: String?,
    @field: SerializedName("isi")
    val isi: String?,
    @field: SerializedName("url_gambar")
    val url_gambar: String?
) : Parcelable

@Parcelize
data class Kategori(
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("nama")
    val nama:String?,
) : Parcelable
