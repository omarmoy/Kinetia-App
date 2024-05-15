package com.dam2.proyectocliente.utils

import java.time.DateTimeException
import java.time.LocalDate

fun isDateValid(dia: Int, mes: Int, anio: Int): Boolean {
    return try {
        LocalDate.of(anio, mes, dia)
        true
    } catch (e: DateTimeException){
        false
    }
}

fun isDateActivityValid(dia: Int, mes: Int, anio: Int): Boolean {
    return try {
        val fecha = LocalDate.of(anio, mes, dia)
        fecha > LocalDate.now()
    } catch (e: DateTimeException){
        false
    }
}


fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}



fun textFieldEmpty(lista: ArrayList<String>): Boolean{
    for (campo in lista){
        if(campo == "")
            return true
    }
    return false
}

fun arePasswordsSame(passwd1: String, passwd2: String): Boolean {
    return passwd1 == passwd2
}