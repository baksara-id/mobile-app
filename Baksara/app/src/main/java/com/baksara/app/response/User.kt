package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("langganan")
    val langganan: Langganan?,
    @field: SerializedName("name")
    val name: String?,
    @field: SerializedName("email")
    val email: String?,
    @field: SerializedName("token")
    val token: String?,
    @field: SerializedName("avatar")
    val avatar: String?,
    @field: SerializedName("exp")
    val exp: Int?,
    @field: SerializedName("level")
    val level: Int?,
    @field: SerializedName("jumlah_scan")
    val jumlah_scan: Int?,
    @field: SerializedName("kadaluarsa")
    val kadaluarsa: String?,
    @field: SerializedName("lencanas")
    val lencanaUsers: List<Lencana>?,
    @field: SerializedName("riwayat_belajars")
    val riwayatBelajar: RiwayatBelajar?,
) : Parcelable


