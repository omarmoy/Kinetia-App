package com.dam2.proyectocliente.models

import kotlinx.serialization.Serializable

@Serializable
class Reservations (
    val id: Long,
    val contactName: String,
    val contactPicture: String,
)