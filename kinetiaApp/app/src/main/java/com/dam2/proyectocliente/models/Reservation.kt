package com.dam2.proyectocliente.models

import kotlinx.serialization.Serializable

@Serializable
class Reservation (
    val contactId: Long,
    val contactName: String,
    val contactPicture: String,
)