package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.network.request.SignUpRequest
import com.dam2.proyectocliente.network.request.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface SignUpApiService {
    @POST("/auth/verify")
    suspend fun exit(@Body verify: VerifyRequest): Boolean

    @POST("/auth/signUp")
    suspend fun signUp(@Body signUp: SignUpRequest): Boolean

}

object SignUpApi{
    val retrofitService: SignUpApiService by lazy {
        retrofit.create(SignUpApiService::class.java)
    }
}