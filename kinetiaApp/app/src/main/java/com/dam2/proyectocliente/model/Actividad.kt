package com.dam2.proyectocliente.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.proyectocliente.R

data class Actividad(
    val id: Int =0, // TODO el id viene de la api
    @DrawableRes val imagen: Int = R.drawable.noimagen,
    var titulo: String,
    @StringRes var contenidoPrueba: Int = R.string.vacio, //TODO BORRAR
    var contenido: String ="",
    val anuncianteID: Int,
    val anunciante: String,
    var fecha: Fecha,
    val fechaPublicacion: Fecha = Fecha.ahora(),
    var precio: Float? = null,
    var ubicacion: String,
    var categoria: Categoria? = null,
    var destacado: Boolean = false,
    //TODO
    var plazas: Int = 1,
    var plazasDisponibles: Int = 1,
    val reservas: ArrayList<Contacto> = ArrayList()
){
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Actividad
        return this.id == other.id
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + imagen
        result = 31 * result + titulo.hashCode()
        result = 31 * result + contenidoPrueba
        result = 31 * result + anuncianteID
        result = 31 * result + anunciante.hashCode()
        result = 31 * result + fecha.hashCode()
        result = 31 * result + fechaPublicacion.hashCode()
        result = 31 * result + (precio?.hashCode() ?: 0)
        result = 31 * result + (ubicacion?.hashCode() ?: 0)
        result = 31 * result + (categoria?.hashCode() ?: 0)
        result = 31 * result + destacado.hashCode()
        return result
    }

    fun addReserva(contacto: Contacto){
        reservas.add(contacto)
        this.plazasDisponibles--
    }

    fun removeReserva(contacto: Contacto){
        reservas.remove(contacto)
        this.plazasDisponibles++
    }

}