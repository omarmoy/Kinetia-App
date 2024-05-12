package com.dam2.proyectocliente.models

import com.dam2.proyectocliente.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Message(
    var id: Long? = null,
    val sender: Long,
    val recipient: Long,
    val content: String,
    @Serializable(with = InstantSerializer::class)
    val sentAt: Instant = Instant.now(),
    var isRead: Boolean = false

)


