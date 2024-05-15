package com.dam2.proyectocliente.network.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyRequest (
    val value: String
)