package com.dam2.proyectocliente.model

import java.time.LocalDateTime

data class Mensaje(
    val idContacto: Int,
    val fecha: LocalDateTime,
    val contenido: String,
    var leido: Boolean
)