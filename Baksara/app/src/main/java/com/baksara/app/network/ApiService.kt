package com.baksara.app.network

import com.baksara.app.response.GraphQLRequest
import com.baksara.app.response.GraphQLResponse
import okhttp3.MultipartBody
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
    @POST("predict")
    suspend fun predict(
        @Part file: MultipartBody.Part
    )
}