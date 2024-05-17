package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.User
import com.dam2.proyectocliente.network.LoginApi
import com.dam2.proyectocliente.network.request.Login

class LoginRepository {
    suspend fun login (login: Login): User?{
        return try {
            LoginApi.retrofitService.login(login)
        } catch (e: Exception) {
            println(e)
            null
        }
    }

    suspend fun deleteUser(userId: Long) {
        LoginApi.retrofitService.deleteUser(userId)
    }

    suspend fun getActivities(userId: Long): ArrayList<Activity>{
        return try {
            LoginApi.retrofitService.getActivities(userId)
        }catch (e: Exception){
            arrayListOf()
        }
    }

    suspend fun getAdvertisements(userId: Long): ArrayList<Advertisement>{
        return try {
            LoginApi.retrofitService.getAdvertisements(userId)
        }catch (e: Exception){
            arrayListOf()
        }
    }
}