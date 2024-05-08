package com.dam2.proyectocliente.utils

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.Chat


fun umbral(): Int {
    return 10
}

fun buscarActividad(
    actividades: ArrayList<Activity>,
    cadenaABuscar: String,
    umbral: Int = umbral()
): ArrayList<Activity> {
    val lista = mutableListOf<Pair<Activity, Int>>()
    for (actividad in actividades) {
        val distancia = calcularDistanciaLevenshtein(actividad.title, cadenaABuscar)
        if (distancia <= umbral)
            lista.add(actividad to distancia)
    }
    val listaOrdenada = lista.sortedBy { it.second }
    val resultado = ArrayList<Activity>()
    for ((actividad, _) in listaOrdenada) {
        resultado.add(actividad)
    }
    return resultado
}

fun buscarAnuncio(
    advertisements: ArrayList<Advertisement>,
    cadenaABuscar: String,
    umbral: Int = umbral()
): ArrayList<Advertisement> {
    //TODO
    return ArrayList()
}

fun buscarContacto(
    chats: ArrayList<Chat>,
    cadenaABuscar: String,
    umbral: Int = umbral()
): ArrayList<Chat> {
    val lista = mutableListOf<Pair<Chat, Int>>()
    for (contacto in chats) {
        val distancia = calcularDistanciaLevenshtein(contacto.contactName, cadenaABuscar)
        if (distancia <= umbral)
            lista.add(contacto to distancia)
    }
    val listaOrdenada = lista.sortedBy { it.second }
    val resultado = ArrayList<Chat>()
    for ((contacto, _) in listaOrdenada) {
        resultado.add(contacto)
    }
    return resultado
}

/**
 * Algoritmo para calcular la distancia de Levenshtein entre dos string
 */
fun calcularDistanciaLevenshtein(s1: String, s2: String): Int {
    val len0 = s1.length + 1
    val len1 = s2.length + 1
    val cost = Array(len0) { IntArray(len1) }

    for (i in 0 until len0) {
        cost[i][0] = i
    }
    for (j in 0 until len1) {
        cost[0][j] = j
    }

    for (i in 1 until len0) {
        for (j in 1 until len1) {
            val deletion = cost[i - 1][j] + 1
            val insertion = cost[i][j - 1] + 1
            val substitution =
                if (s1[i - 1] == s2[j - 1]) cost[i - 1][j - 1] else cost[i - 1][j - 1] + 1
            cost[i][j] = minOf(deletion, insertion, substitution)
        }
    }

    return cost[len0 - 1][len1 - 1]
}