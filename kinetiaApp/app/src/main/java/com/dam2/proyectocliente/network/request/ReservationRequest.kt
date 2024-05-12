package com.dam2.proyectocliente.network.request

import kotlinx.serialization.Serializable

@Serializable
data class ReservationRequest (
    val userId: Long,
    val activityId: Long
)