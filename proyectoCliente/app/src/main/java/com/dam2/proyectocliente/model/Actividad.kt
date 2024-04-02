package com.dam2.proyectocliente.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Actividad(
    val id: Int,
    @DrawableRes val imagen: Int,
    val titulo: String,
    @StringRes val contenido: Int,
    val anuncianteID: Int,
    val anunciante: String,
    val fecha: Fecha,
    val fechaPublicacion: Fecha = Fecha.ahora(),
    val duracion: Int? = null,
    val precio: Float? = null,
    val ubicacion: String? = null,
    val categoria: Categoria? = null,
    val destacado: Boolean = false
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
        result = 31 * result + contenido
        result = 31 * result + anuncianteID
        result = 31 * result + anunciante.hashCode()
        result = 31 * result + fecha.hashCode()
        result = 31 * result + fechaPublicacion.hashCode()
        result = 31 * result + (duracion ?: 0)
        result = 31 * result + (precio?.hashCode() ?: 0)
        result = 31 * result + (ubicacion?.hashCode() ?: 0)
        result = 31 * result + (categoria?.hashCode() ?: 0)
        result = 31 * result + destacado.hashCode()
        return result
    }
}