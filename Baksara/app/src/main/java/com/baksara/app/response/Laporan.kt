package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Laporan (
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("judul")
    val judul: String?,
    @field: SerializedName("isi")
    val isi: String?
) : Parcelable