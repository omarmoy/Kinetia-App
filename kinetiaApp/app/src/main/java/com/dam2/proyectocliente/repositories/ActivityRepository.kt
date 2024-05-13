package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.network.ActivityApi
import com.dam2.proyectocliente.network.request.ActivityRequest

class ActivityRepository {

    //CRUD ACTIVITY
    suspend fun add (activity: Activity): Long?{
        return try {
            ActivityApi.retrofitService.addActivity(activity)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun edit(activity: Activity): Boolean{
        return try {
            ActivityApi.retrofitService.editActivity(activity)
        }catch (e: Exception){
            false
        }
    }

    suspend fun delete(activityId: Long): Boolean{
        return try {
            ActivityApi.retrofitService.deleteActivity(activityId)
        }catch (e: Exception){
            false
        }
    }

    //RESERVATIONS
    suspend fun reserve (userId: Long, activityId: Long): Boolean{
        val reservationRequest = ActivityRequest(userId, activityId)
        return try {
            ActivityApi.retrofitService.addReservation(reservationRequest)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun cancelReserve(userId: Long, activityId: Long): Boolean{
        val reservationRequest = ActivityRequest(userId, activityId)
        return try {
            ActivityApi.retrofitService.deleteReservation(reservationRequest)
        }catch (e: Exception){
            println(e)
            false
        }
    }

    //FAVS
    suspend fun addFav (userId: Long, activityId: Long): Boolean{
        val activityRequest = ActivityRequest(userId, activityId)
        return try {
            ActivityApi.retrofitService.addFav(activityRequest)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteFav(userId: Long, activityId: Long): Boolean{
        val activityRequest = ActivityRequest(userId, activityId)
        return try {
            ActivityApi.retrofitService.deleteFav(activityRequest)
        }catch (e: Exception){
            println(e)
            false
        }
    }

}