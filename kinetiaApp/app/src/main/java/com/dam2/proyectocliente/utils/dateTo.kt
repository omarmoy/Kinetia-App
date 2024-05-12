package com.dam2.proyectocliente.utils

import java.time.LocalDate
import java.time.LocalDateTime


fun dateToString(localDateTime: LocalDateTime): String {
    return "${localDateTime.dayOfMonth}/${localDateTime.monthValue}/${localDateTime.year}"
}

fun timeToString(localDateTime: LocalDateTime): String {
    val minuts =
        if (localDateTime.minute < 10) "0".plus(localDateTime.minute) else localDateTime.minute
    return "${localDateTime.hour}:${minuts}"
}

fun showDate(localDateTime: LocalDateTime): String {
    val today = LocalDate.now()
    val yesterday = LocalDate.now().minusDays(1)
    val localDate: LocalDate = localDateTime.toLocalDate()
    if (today.isEqual(localDate))
        return timeToString(localDateTime)
    if (yesterday.isEqual(localDate.minusDays(1)))
        return "ayer"
    return dateToString(localDateTime)
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


