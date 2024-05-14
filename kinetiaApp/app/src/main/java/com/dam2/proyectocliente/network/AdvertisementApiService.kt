package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.network.request.ActivityRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface AdvertisementApiService {

    @POST("/advertisements")
    suspend fun addAdvertisement(@Body advertisement: Advertisement): Long

    @PUT("/advertisements")
    suspend fun editAdvertisement(@Body advertisement: Advertisement): Boolean

    @DELETE("/advertisements/{adId}")
    suspend fun deleteAdvertisement(@Path("adId") adId: Long): Boolean


}

object AdvertisementApi{
    val retrofitService: AdvertisementApiService by lazy {
        retrofit.create(AdvertisementApiService::class.java)
    }
}