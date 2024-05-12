package com.dam2.proyectocliente.network

import com.dam2.proyectocliente.models.Message
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface MessageApiService {

    @POST("/message")
    suspend fun sendMessage(@Body message: Message): Boolean

    @PUT("/message/{messageId}")
    suspend fun messageRead(@Path("messageId") messageId: Long): Boolean

    @DELETE("/message/{userId}/{contactId}")
    suspend fun deleteContact(@Path("userId") userId: Long, @Path("contactId") contactId: Long): Boolean
}

object MessageApi{
    val retrofitService: MessageApiService by lazy {
        retrofit.create(MessageApiService::class.java)
    }
}