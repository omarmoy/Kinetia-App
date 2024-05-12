package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Message
import com.dam2.proyectocliente.network.MessageApi

class MessageRepository {
    suspend fun sendMessage (message: Message): Boolean{
        return try {
            MessageApi.retrofitService.sendMessage(message)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun messageRead(messageId: Long): Boolean{
        return try {
            MessageApi.retrofitService.messageRead(messageId)
        }catch (e: Exception){
            false
        }
    }

    suspend fun deleteContact(userId: Long, contactId: Long): Boolean{
        return try {
            MessageApi.retrofitService.deleteContact(userId, contactId)
        }catch (e: Exception){
            false
        }
    }
}