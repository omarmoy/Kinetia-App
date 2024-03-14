package com.dam2.proyectocliente.model

import java.util.Date

data class Anuncio(
    val id: Int,
    val titulo: String,
    val contenido: String,
    val anuncianteNombre: String,
    val anuncianteID: String,
    val fecha: Date
)
