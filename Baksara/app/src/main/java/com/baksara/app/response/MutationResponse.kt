package com.baksara.app.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MutationResponse(
    @field: SerializedName("response")
    val response: String?,
    @field: SerializedName("response2")
    val response2:String?,
): Parcelable
