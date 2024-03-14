package com.dam2.proyectocliente.model

enum class Rol{
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
    private val actividades: ArrayList<Actividad>,
    private val anuncios: ArrayList<Anuncio>,
    private val conversaciones: ArrayList<Conversacion>
) {
    fun addActividad(actividad: Actividad){
        actividades.add(actividad)
    }
    fun eliminarActividad(actividad: Actividad){
        actividades.remove(actividad)
    }
    fun addAnuncio(anuncio: Anuncio){
        anuncios.add(anuncio)
    }
    fun eleminarAnuncio(anuncio: Anuncio){
        anuncios.remove(anuncio)
    }
    fun addConversacion(conversacion: Conversacion){
        conversaciones.add(conversacion)
    }
    fun eliminarConversacion(conversacion: Conversacion){
        conversaciones.remove(conversacion)
    }
}