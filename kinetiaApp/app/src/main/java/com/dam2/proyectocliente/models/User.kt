package com.dam2.proyectocliente.models

import com.dam2.proyectocliente.utils.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

enum class Role {
    PROVIDER, CONSUMER, ADMIN
}

@Serializable
data class User(
    val id: Long,
    val email: String,
    val password: String = "",
    val role: Role,
    val name: String,
    val surname: String,
    val secondSurname: String = "",
    @Serializable(with = LocalDateSerializer::class)
    val birthDate: LocalDate,
    val profilePicture: String,
    val company: String = "",
    val cif: String = "",
    val address: String = "",

    val advertisements: ArrayList<Advertisement> = ArrayList(),
    val activitiesOffered: ArrayList<Activity> = ArrayList(),
    val activitiesFav: ArrayList<Activity> = ArrayList(),
    val activitiesReserved: ArrayList<Activity> = ArrayList(),
    val chats: ArrayList<Chat> = ArrayList()
) {

    fun fullName(): String {
        return this.name + " " + this.surname + " " + this.secondSurname
    }

    /**
    ACTIVIDADES
     */
    fun addActividadFav(activity: Activity): Boolean {
        return activitiesFav.add(activity)
    }

    fun eliminarActividadFav(activity: Activity): Boolean {
        return activitiesFav.remove(activity)
    }

    fun esFavorita(activity: Activity): Boolean {
        return activitiesFav.contains(activity)
    }

    fun addActividadOferta(activity: Activity): Boolean {
        return activitiesOffered.add(activity)
    }

    fun eliminarActividadOferta(activity: Activity): Boolean {
        return activitiesOffered.remove(activity)
    }

    /**
    ANUNCIOS
     */
    fun addAnuncio(advertisement: Advertisement): Boolean {
        return advertisements.add(advertisement)
    }

    fun eliminarAnuncio(advertisement: Advertisement): Boolean {
        return advertisements.remove(advertisement)
    }

    /**
    CONVERSACIONES
     */
    fun addContacto(chat: Chat): Boolean {
        return chats.add(chat)
    }

    fun eliminarContacto(chat: Chat): Boolean {
        return chats.remove(chat)
    }

    fun marcarMensajeLeido(chat: Chat) {
        val indice = chats.indexOf(chat)
        chats[indice].newMessage = false
    }

    fun addMensaje(chat: Chat, mensaje: Message) {
        val indice = chats.indexOf(chat)
        chats[indice].addMensaje(mensaje)
    }

    fun tieneMensajesSinLeer(): Boolean {
        for (contacto in chats) {
            if (contacto.newMessage)
                return true
        }
        return false
    }

    /**
    RESERVAS MODO PRO
     */
    //TODO???

    /**
    RESERVAS MODO CONSUMIDOR
     */

    fun reseservar(activity: Activity){
        activitiesReserved.add(activity)
//        activity.addReserva(Chat(id=this.id, contactName=this.nombreCompleto(), contactPicture=this.profilePicture))
        //TODO: api
    }
    fun cancelarReserva(activity: Activity){
        activitiesReserved.remove(activity)
//        activity.removeReserva(Chat(id=this.id, contactName=this.nombreCompleto(), contactPicture=this.profilePicture))
        //TODO: api
    }
}

