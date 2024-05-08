package com.dam2.proyectocliente.utils

import java.time.LocalDate
import java.time.LocalDateTime


fun toStringFecha(localDateTime: LocalDateTime): String {
    return "${localDateTime.dayOfMonth}/${localDateTime.monthValue}/${localDateTime.year}"
}

fun toStringHora(localDateTime: LocalDateTime): String {
    val minutos =
        if (localDateTime.minute < 10) "0".plus(localDateTime.minute) else localDateTime.minute
    return "${localDateTime.hour}:${minutos}"
}

fun mostrarFecha(localDateTime: LocalDateTime): String {
    val hoy = LocalDate.now()
    val ayer = LocalDate.now().minusDays(1)
    val localDate: LocalDate = localDateTime.toLocalDate()
    if (hoy.isEqual(localDate))
        return toStringHora(localDateTime)
    if (ayer.isEqual(localDate.minusDays(1)))
        return "ayer"
    return toStringFecha(localDateTime)
}

fun diaString(localDateTime: LocalDateTime): String {
    return localDateTime.dayOfMonth.toString()
}

fun mesString(localDateTime: LocalDateTime): String {
    return localDateTime.monthValue.toString()
}

fun anioString(localDateTime: LocalDateTime): String {
    return localDateTime.year.toString()
}

fun horaString(localDateTime: LocalDateTime): String {
    return localDateTime.hour.toString()
}

fun minutosString(localDateTime: LocalDateTime): String {
    return localDateTime.minute.toString()
}


