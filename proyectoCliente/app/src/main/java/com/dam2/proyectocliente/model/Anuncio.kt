package com.dam2.proyectocliente.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.proyectocliente.R

class Anuncio(
    val id: Int,
    @DrawableRes
    val fotoAnunciante: Int,
    var titulo: String,
    @StringRes
    val contenidoInt: Int = R.string.vacio,  //TODO eliminar
    var contenido: String = "",
    val anuncianteID: Int,
    val anunciante: String,
    val fecha: Fecha,
    var localidad: String = "Sevilla"
) {

}