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

    suspend fun getActivities(): ArrayList<Activity>{
        return try {
            LoginApi.retrofitService.getActivities()
        }catch (e: Exception){
            arrayListOf()
        }
    }

    suspend fun getAdvertisements(): ArrayList<Advertisement>{
        return try {
            LoginApi.retrofitService.getAdvertisements()
        }catch (e: Exception){
            arrayListOf()
        }
    }
}