package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse (
    @field:SerializedName("prob")
    val result: String?,
//    @field:SerializedName("kelas_tertinggi")
//    val kelas_tertinggi: String
): Parcelable