package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.network.AdvertisementApi

class AdvertisementRepository {

    suspend fun post (advertisement: Advertisement): Long?{
        return try {
            AdvertisementApi.retrofitService.addAdvertisement(advertisement)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun edit(advertisement: Advertisement): Boolean{
        return try {
            AdvertisementApi.retrofitService.editAdvertisement(advertisement)
        }catch (e: Exception){
            false
        }
    }

    suspend fun delete(adId: Long): Boolean{
        return try {
            AdvertisementApi.retrofitService.deleteAdvertisement(adId)
        }catch (e: Exception){
            false
        }
    }



}