package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("langganan_id")
    val langganan_id: Int?,
    @field: SerializedName("name")
    val name: String?,
    @field: SerializedName("email")
    val email: String?,
    @field: SerializedName("password")
    val password: String?,
    @field: SerializedName("token")
    val token: String?,
    @field: SerializedName("avatar")
    val avatar: String?,
    @field: SerializedName("exp")
    val exp: Double?,
    @field: SerializedName("level")
    val level: Int?,
    @field: SerializedName("jumlah_scan")
    val jumlah_scan: Int?,
    @field: SerializedName("kadaluarsa")
    val kadaluarsa: String?,
    @field: SerializedName("user_lencanas")
    val lencanaUsers: List<Lencana>?,
    @field: SerializedName("user_tantangans")
    val tantanganUsers: List<Tantangan>?,
    @field: SerializedName("user_levels")
    val levelUsers: List<Level>?,
) : Parcelable


