package com.dam2.proyectocliente.model

class Conversacion(
    val contacto: String,
    val contactoID: Int,
    private val mensajes: ArrayList<Mensaje>
) {
    public fun addMensaje(mensaje: Mensaje){
        mensajes.add(mensaje)
    }
    public fun eliminarMensaje(mensaje: Mensaje){
        mensajes.remove(mensaje)
    }
}