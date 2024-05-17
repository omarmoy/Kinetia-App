package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.User
import com.dam2.proyectocliente.network.request.Login
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface LoginApiService {
    @POST("/auth/login")
    suspend fun login(@Body login: Login): User

    @DELETE("/auth/{userId}")
    suspend fun deleteUser (@Path("userId") userId: Long)

    @GET("/activities/{userId}")
    suspend fun getActivities(@Path("userId") userId: Long): ArrayList<Activity>

    @GET("/advertisements/{userId}")
    suspend fun getAdvertisements(@Path("userId") userId: Long): ArrayList<Advertisement>

}

object LoginApi{
    val retrofitService: LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }
}