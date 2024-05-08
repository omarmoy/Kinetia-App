package com.dam2.proyectocliente.models

import kotlinx.serialization.Serializable

@Serializable
class Chat(
    val id: Long,
    val contactName: String,
    val contactPicture: String,
    val messages: ArrayList<Message> = ArrayList(),
    var newMessage: Boolean = false
) {

    fun addMensaje(mensaje: Message) {
        messages.add(mensaje)
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Chat
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}