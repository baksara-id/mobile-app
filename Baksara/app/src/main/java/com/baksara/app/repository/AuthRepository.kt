package com.baksara.app.repository

import com.baksara.app.local.UserPreferences
import com.baksara.app.network.ApiService
import com.baksara.app.response.GraphQLRequest
import com.baksara.app.response.GraphQLResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository(
    private val userpref: UserPreferences,
    private val service: ApiService
) {
    suspend fun register(email: String, name: String, password: String): Flow<Result<GraphQLResponse>> = flow {
        try {
            val response = service.graphql(
                "application/json",
                GraphQLRequest(
                    """
                        mutation {
                            register(email: "$email", name: "$name", password: "$password") {
                                id
                                name
                                email
                                password
                            }
                        }
                    """.trimIndent()
                )
            )

            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    suspend fun login(){

    }

    suspend fun logout(){

    }

    suspend fun getAllBadge(){

    }

    companion object {
        @Volatile
        private var INSTANCE: AuthRepository? = null

        fun getInstance(userpref: UserPreferences, apiService: ApiService): AuthRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = AuthRepository(userpref,apiService)
                }
                return INSTANCE as AuthRepository
            }
        }
    }
}