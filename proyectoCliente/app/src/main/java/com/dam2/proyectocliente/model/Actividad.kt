package com.dam2.proyectocliente.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.Date

data class Actividad (
    val id: Int,
    @DrawableRes val imagen: Int,
    val titulo: String,
    @StringRes val contendio: Int,
    val anuncianteID: Int,
    val anunciante: String,
    val fecha: Date,
    val duracion: Int? = null,
    val precio: Float? = null,
    val ubicacion: String? = null,
    val categoria: String? = null,
)