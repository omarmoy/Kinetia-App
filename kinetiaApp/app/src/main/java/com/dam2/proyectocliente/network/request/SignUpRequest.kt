package com.dam2.proyectocliente.network.request

import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.utils.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class SignUpRequest(
    val email: String,
    val role: Role,
    val password: String,
    val name: String,
    val surname: String,
    val secondSurname: String?,
    @Serializable(with = LocalDateSerializer::class)
    val birthDate: LocalDate?,
    val profilePicture: String,
    val company: String,
    val cif: String?,
    val address: String
)

