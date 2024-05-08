package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.User
import com.dam2.proyectocliente.network.request.Login
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//private const val BASE_URL = "http://127.0.0.1:8080"
//private const val BASE_URL = "http://localhost:8080"
private const val BASE_URL = "http://10.0.2.2:8080"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
interface UserApiService {
    @POST("/auth/login")
    suspend fun login(@Body login: Login): User

    @GET("/activities")
    suspend fun getActivities(): ArrayList<Activity>

}

object UserApi{
    val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}