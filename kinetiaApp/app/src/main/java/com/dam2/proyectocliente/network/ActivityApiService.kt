package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.network.request.ActivityRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HTTP
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


    @POST("/auth/reservation")
    suspend fun addReservation(@Body reservation: ActivityRequest): Boolean

    //    @DELETE("/auth/reservation")
    @HTTP(method = "DELETE", path = "/auth/reservation", hasBody = true)
    suspend fun deleteReservation(@Body reservation: ActivityRequest): Boolean


    @POST("/auth/fav")
    suspend fun addFav(@Body activity: ActivityRequest): Boolean

    @HTTP(method = "DELETE", path = "/auth/fav", hasBody = true)
    suspend fun deleteFav(@Body activity: ActivityRequest): Boolean


}

object ActivityApi{
    val retrofitService: ActivityApiService by lazy {
        retrofit.create(ActivityApiService::class.java)
    }
}