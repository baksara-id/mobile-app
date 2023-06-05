package com.baksara.app.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RiwayatBelajar(
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("user_id")
    val user_id: Int?,
    @field: SerializedName("nomor_modul")
    val nomor_modul: Int?,
    @field: SerializedName("nomor_pelajaran")
    val nomor_pelajaran: Int?
) : Parcelable
