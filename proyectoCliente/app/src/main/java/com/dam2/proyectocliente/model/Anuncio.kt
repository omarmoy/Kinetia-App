package com.dam2.proyectocliente.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.time.LocalDateTime
import java.util.Date

class Anuncio(
    val id: Int,
    @DrawableRes
    val fotoAnunciante: Int,
    val titulo: String,
    @StringRes
    val contendio: Int,
    val anuncianteID: Int,
    val anunciante: String,
    val fecha: Fecha,
) {

}