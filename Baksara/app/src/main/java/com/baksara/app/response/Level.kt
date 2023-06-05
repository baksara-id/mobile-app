package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Level (
    @field: SerializedName("id")
    val id: Int?,
    @field: SerializedName("nama")
    val nama: String?
) : Parcelable
