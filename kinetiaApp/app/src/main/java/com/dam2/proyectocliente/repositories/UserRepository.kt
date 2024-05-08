package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.User
import com.dam2.proyectocliente.network.UserApi
import com.dam2.proyectocliente.network.request.Login

class UserRepository {
    suspend fun login (login: Login): User?{
        return try {
            UserApi.retrofitService.login(login)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getActivities(): ArrayList<Activity>{
        return try {
            UserApi.retrofitService.getActivities()
        }catch (e: Exception){
            arrayListOf()
        }
    }

    suspend fun getAdvertisements(): ArrayList<Advertisement>{
        return try {
            UserApi.retrofitService.getAdvertisements()
        }catch (e: Exception){
            arrayListOf()
        }
    }
}