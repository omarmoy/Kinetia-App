package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Reservation
import com.dam2.proyectocliente.network.ReservationApi
import com.dam2.proyectocliente.network.request.ReservationRequest

class ReservationRepository {
    suspend fun reserve (userId: Long, activityId: Long): Boolean{
        val reservationRequest = ReservationRequest(userId, activityId)
        return try {
            ReservationApi.retrofitService.addReservation(reservationRequest)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun cancel(userId: Long, activityId: Long): Boolean{
        val reservationRequest = ReservationRequest(userId, activityId)
        return try {
            ReservationApi.retrofitService.deleteReservation(reservationRequest)
        }catch (e: Exception){
            println(e)
            false
        }
    }
}