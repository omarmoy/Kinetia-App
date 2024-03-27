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
    val duracion: Int? = null,
    val precio: Float? = null,
    val ubicacion: String? = null,
    val categoria: String? = null,
)