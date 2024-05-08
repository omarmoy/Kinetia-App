package com.dam2.proyectocliente.models

import com.dam2.proyectocliente.utils.InstantSerializer
import com.dam2.proyectocliente.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDateTime

@Serializable
data class Activity(

    val id: Long? = null,
    var title: String,
    var description: String,
    var picture: String,
    val userId: Long,
    val userName: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    var date: LocalDateTime,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant = Instant.now(),
    var price: Double,
    var location: String,
    var category: Category,
    var featured: Boolean = false,
    var vacancies: Int,
    var availableVacancies: Int = -1,
    val reservations: ArrayList<Reservations> = ArrayList()
){




    fun addReserva(reservation: Reservations){
        reservations.add(reservation)
        this.availableVacancies--
    }

    fun removeReserva(reservation: Reservations){
        reservations.remove(reservation)
        this.availableVacancies++
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as Activity
        return this.id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }


}