package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Langganan (
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("nama")
    val nama: String?,
    @field: SerializedName("harga")
    val harga: Float?,
    @field: SerializedName("durasi")
    val durasi: Int?,
) : Parcelable