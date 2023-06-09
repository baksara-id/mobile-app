package com.baksara.app.response

import com.google.gson.annotations.SerializedName


data class TranslatorResponse(
    @field:SerializedName("hasil")
    val hasil: String?,
)