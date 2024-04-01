package com.dam2.proyectocliente.model

class Contacto(
    val id: Int,
    val nombre: String,
    val foto: Int,
    val mensajes: ArrayList<Mensaje>,
    var mensajeNuevo: Boolean = false
) {
    fun addMensaje(mensaje: Mensaje) {
        mensajes.add(mensaje)
    }

    fun eliminarMensaje(mensaje: Mensaje) {
        mensajes.remove(mensaje)
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Contacto
        return this.id == other.id
    }


}