package com.baksara.app.network

import com.baksara.app.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("graphql")
    suspend fun graphql(
        @Header("Content-Type") type: String,
        @Body body: GraphQLRequest
    ): GraphQLResponse

    @Multipart
    @POST("kelas")
    suspend fun predict(
        @Part file: MultipartBody.Part,
        @Part("actual_class") description: RequestBody,
    ): PredictResponse

    @POST("tojavanese")
    suspend fun translator(
        @Body body: TranslatorRequest
    ) : TranslatorResponse

    @POST("insidemyhead")
    suspend fun translator_tuan_scanner(
        @Body body: Translatorv2Request
    ) : Translatorv2Response

    @Multipart
    @POST("scanner")
    suspend fun scanner(
        @Part file: MultipartBody.Part,
    ): ScannerResponse
}