package com.dam2.proyectocliente.network.request

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val email: String,
    val password: String
)
