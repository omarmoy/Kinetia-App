package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Activity
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:8080"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
interface ActivityApiService {

    @POST("/activities")
    suspend fun addActivity(activity: Activity): Long

    @PUT("/activities")
    suspend fun editActivity(activity: Activity): Boolean

    @DELETE("/activities/{activityId}")
    suspend fun deleteActivity(@Path("activityId") activityId: Long): Boolean
}

object ActivityApi{
    val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }
}