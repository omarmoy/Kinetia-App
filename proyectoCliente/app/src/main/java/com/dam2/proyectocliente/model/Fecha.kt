package com.dam2.proyectocliente.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Fecha (
    localDateTime: LocalDateTime
) {
    val localDate: LocalDate = localDateTime.toLocalDate()
    val localTime: LocalTime = localDateTime.toLocalTime()
    val localDateTime: LocalDateTime = localDateTime

    fun toStringFecha(): String{
        return "${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}"
    }

    fun toStringHora(): String{
        val minutos = if(localTime.minute<10) "0".plus(localTime.minute) else localTime.minute
        return "${localTime.hour}:${minutos}"
    }

    fun mostrarFecha(): String{
        val hoy = LocalDate.now()
        val ayer = LocalDate.now().minusDays(1)
        if(hoy.isEqual(localDate))
            return toStringHora()
        if(ayer.isEqual(localDate.minusDays(1)))
            return "ayer"
        return toStringFecha()
    }

    fun diaString(): String{
        return localDate.dayOfMonth.toString()
    }
    fun mesString(): String{
        return localDate.monthValue.toString()
    }
    fun anioString(): String{
        return localDate.year.toString()
    }

    fun horaString(): String{
        return localTime.hour.toString()
    }
    fun minutosString(): String{
        return localTime.minute.toString()
    }

    companion object {
        fun ahora(): Fecha {
            return Fecha(LocalDateTime.now())
        }
    }
}