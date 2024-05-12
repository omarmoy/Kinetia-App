package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.network.ActivityApi

class ActivityRepository {
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
}