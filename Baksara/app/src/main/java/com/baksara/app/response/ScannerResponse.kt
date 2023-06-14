package com.baksara.app.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScannerResponse (
    @field:SerializedName("result")
    val result: List<List<String>>
): Parcelable