package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.network.request.ReservationRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HTTP
import retrofit2.http.POST

interface ReservationApiService {
    @POST("/auth/reservation")
    suspend fun addReservation(@Body reservation: ReservationRequest): Boolean

    //    @DELETE("/auth/reservation")
    @HTTP(method = "DELETE", path = "/auth/reservation", hasBody = true)
    suspend fun deleteReservation(@Body reservation: ReservationRequest): Boolean
}

object ReservationApi {
    val retrofitService: ReservationApiService by lazy {
        retrofit.create(ReservationApiService::class.java)
    }
}