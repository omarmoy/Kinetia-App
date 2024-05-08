package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Activity
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
        return UserApi.retrofitService.getActivities()
    }
}