package com.baksara.app.response

import com.google.gson.annotations.SerializedName

data class Translatorv2Response(
	@field:SerializedName("hasil")
	val hasil: List<String?>? = null
)
