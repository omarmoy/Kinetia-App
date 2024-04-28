package com.dam2.proyectocliente.model

import com.dam2.proyectocliente.controlador.DatosPrueba
import java.time.LocalDate

enum class Rol {
    OFERTANTE, CONSUMIDOR, ADMINISTRADOR
}

class Usuario(
    val id: Int,
    val mail: String,
    val password: String,
    val rol: Rol,
    val nombre: String,
    val apellido1: String,
    val apellido2: String = "",
    val fechaNaciminto: LocalDate? = null,
    val foto: Int,
    val nombreEmpsesa: String = "",
    val cif: String = "",
    val direccion: String = "",
    val actividadesReservadas: ArrayList<Actividad> = ArrayList(),

    val actividadesFav: ArrayList<Actividad> = ArrayList(),
    val actividadesOfertadas: ArrayList<Actividad> = ArrayList(),
    val anunciosPublicados: ArrayList<Anuncio> = ArrayList(),
    val contactos: ArrayList<Contacto> = ArrayList()
) {

    fun nombreCompleto(): String {
        return this.nombre + " " + this.apellido1 + " " + this.apellido2
    }

    /**
    ACTIVIDADES
     */
    fun addActividadFav(actividad: Actividad): Boolean {
        return actividadesFav.add(actividad)
    }

    fun eliminarActividadFav(actividad: Actividad): Boolean {
        return actividadesFav.remove(actividad)
    }

    fun esFavorita(actividad: Actividad): Boolean {
        return actividadesFav.contains(actividad)
    }

    fun addActividadOferta(actividad: Actividad): Boolean {
        return actividadesOfertadas.add(actividad)
    }

    fun eliminarActividadOferta(actividad: Actividad): Boolean {
        return actividadesOfertadas.remove(actividad)
    }

    /**
    ANUNCIOS
     */
    fun addAnuncio(anuncio: Anuncio): Boolean {
        return anunciosPublicados.add(anuncio)
    }

    fun eliminarAnuncio(anuncio: Anuncio): Boolean {
        return anunciosPublicados.remove(anuncio)
    }

    /**
    CONVERSACIONES
     */
    fun addContacto(contacto: Contacto): Boolean {
        return contactos.add(contacto)
    }

    fun eliminarContacto(contacto: Contacto): Boolean {
        return contactos.remove(contacto)
    }

    fun marcarMensajeLeido(contacto: Contacto) {
        val indice = contactos.indexOf(contacto)
        contactos[indice].mensajeNuevo = false
    }

    fun addMensaje(contacto: Contacto, mensaje: Mensaje) {
        val indice = contactos.indexOf(contacto)
        contactos[indice].addMensaje(mensaje)
    }

    fun tieneMensajesSinLeer(): Boolean {
        for (contacto in contactos) {
            if (contacto.mensajeNuevo)
                return true
        }
        return false
    }

    /**
    RESERVAS MODO PRO
     */
    fun addReserva(actividad: Actividad, contacto: Contacto) {
        for (actividadOf in actividadesOfertadas) {
            if (actividadOf == actividad) {
                actividadOf.addReserva(contacto)
                return
            }
        }
    }


    fun removeReserva(actividad: Actividad, contacto: Contacto) {
        for (actividadOf in actividadesOfertadas) {
            if (actividadOf == actividad) {
                actividadOf.removeReserva(contacto)
                return
            }
        }

    }

    /**
    RESERVAS MODO CONSUMIDOR
     */

    fun reseservar(actividad: Actividad){
        actividadesReservadas.add(actividad)
        actividad.addReserva(Contacto(id=this.id, nombre=this.nombreCompleto(), foto=this.foto))
        //TODO: api
    }
    fun cancelarReserva(actividad: Actividad){
        actividadesReservadas.remove(actividad)
        actividad.removeReserva(Contacto(id=this.id, nombre=this.nombreCompleto(), foto=this.foto))
        //TODO: api
    }
}

