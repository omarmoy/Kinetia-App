package com.dam2.proyectocliente.model

import java.util.Date

data class Mensaje(
    val fecha: Date,
    val contenido: String,
    val recibido: Boolean
)