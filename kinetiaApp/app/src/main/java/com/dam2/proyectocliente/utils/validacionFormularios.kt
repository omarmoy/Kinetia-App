package com.dam2.proyectocliente.utils

import java.time.DateTimeException
import java.time.LocalDate

fun fechaNacimientoOK(dia: Int, mes: Int, anio: Int): Boolean {
    try {
        LocalDate.of(anio, mes, dia)
        return true
    } catch (e: DateTimeException){
        return false
    }
}

fun validarFechaActividad(dia: Int, mes: Int, anio: Int): Boolean {
    try {
        val fecha = LocalDate.of(anio, mes, dia)
        return fecha > LocalDate.now()
    } catch (e: DateTimeException){
        return false
    }
}

//fun texfieldVacio(t1: String, t2: String, t3: String, t4: String, t5: String): Boolean {
//    if (t1 == "")
//        return true
//    if (t2 == "")
//        return true
//    if (t3 == "")
//        return true
//    if (t4 == "")
//        return true
//    if (t5 == "")
//        return true
//    return false
//}

fun emailValido(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

//fun texfieldVacio(t1: String, t2: String, t3: String): Boolean {
//    if (t1 == "")
//        return true
//    if (t2 == "")
//        return true
//    if (t3 == "")
//        return true
//    return false
//}

fun texfieldVacio(lista: ArrayList<String>): Boolean{
    for (campo in lista){
        if(campo == "")
            return true
    }
    return false
}

fun passwdIguales(passwd1: String, passwd2: String): Boolean {
    return passwd1 == passwd2
}