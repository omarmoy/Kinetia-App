package com.dam2.proyectocliente.models

import com.dam2.proyectocliente.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Advertisement(
    val id: Long? = null,
    val userId: Long,
    val userName: String,
    val userPhoto: String,
    var title: String,
    var description: String,
    @Serializable(with = InstantSerializer::class)
    val creationDate: Instant = Instant.now(),
    var location: String
) {

}