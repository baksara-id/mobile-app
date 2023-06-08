package com.baksara.app.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MutationResponse(
    @field: SerializedName("is_approved")
    val is_approved: Boolean?,
    @field: SerializedName("jawaban")
    val jawaban:String?
): Parcelable
