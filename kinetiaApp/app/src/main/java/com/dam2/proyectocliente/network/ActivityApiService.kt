package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Activity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ActivityApiService {

    @POST("/activities")
    suspend fun addActivity(@Body activity: Activity): Long

    @PUT("/activities")
    suspend fun editActivity(@Body activity: Activity): Boolean

    @DELETE("/activities/{activityId}")
    suspend fun deleteActivity(@Path("activityId") activityId: Long): Boolean
}

object ActivityApi{
    val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }
}