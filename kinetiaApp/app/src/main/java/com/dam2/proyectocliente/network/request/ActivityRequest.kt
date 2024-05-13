package com.dam2.proyectocliente.network.request

import kotlinx.serialization.Serializable

@Serializable
data class ActivityRequest (
    val userId: Long,
    val activityId: Long
)