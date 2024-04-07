package com.dam2.proyectocliente.model

import com.dam2.proyectocliente.controlador.DatosPrueba

enum class Rol {
    OFERTANTE, CONSUMIDOR, ADMINISTRADOR
}

class Usuario(
    val id: Int,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val mail: String,
    val rol: Rol,
    val password: String,
    val foto: Int,
    val nif: String,
    val actividadesFav: ArrayList<Actividad> = ArrayList(),
    val reservas: ArrayList<Actividad> = DatosPrueba.actividades, //TODO: cambiar y poner funcionalidad Reservas
    val actividadesOfertadas: ArrayList<Actividad> = ArrayList(),
    val anunciosPublicados: ArrayList<Anuncio> = ArrayList(),
    val contactos: ArrayList<Contacto> = ArrayList()
) {

    /**
      ACTIVIDADES
     */
    fun addActividadFav(actividad: Actividad): Boolean {
        return actividadesFav.add(actividad)
    }

    fun eliminarActividadFav(actividad: Actividad): Boolean {
        return actividadesFav.remove(actividad)
    }

    fun esFavorita(actividad: Actividad): Boolean{
        return actividadesFav.contains(actividad)
    }

    fun addOfertaActividad(actividad: Actividad): Boolean {
        return actividadesOfertadas.add(actividad)
    }

    fun eliminarOfertaActividad(actividad: Actividad): Boolean {
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

    fun marcarMensajeLeido(contacto: Contacto){
        val indice = contactos.indexOf(contacto)
        contactos[indice].mensajeNuevo = false
    }

    fun addMensaje(contacto: Contacto, mensaje: Mensaje){
        val indice = contactos.indexOf(contacto)
        contactos[indice].addMensaje(mensaje)
    }

    fun tieneMensajesSinLeer(): Boolean{
        for(contacto in contactos){
            if(contacto.mensajeNuevo)
                return true
        }
        return false
    }

}

