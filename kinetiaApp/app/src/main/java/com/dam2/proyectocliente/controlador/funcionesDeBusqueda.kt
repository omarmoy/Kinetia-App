package com.dam2.proyectocliente.controlador

import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Contacto


fun umbral(): Int {
    return 10
}

fun buscarActividad(
    actividades: ArrayList<Actividad>,
    cadenaABuscar: String,
    umbral: Int = umbral()
): ArrayList<Actividad> {
    val lista = mutableListOf<Pair<Actividad, Int>>()
    for (actividad in actividades) {
        val distancia = calcularDistanciaLevenshtein(actividad.titulo, cadenaABuscar)
        if (distancia <= umbral)
            lista.add(actividad to distancia)
    }
    val listaOrdenada = lista.sortedBy { it.second }
    val resultado = ArrayList<Actividad>()
    for ((actividad, _) in listaOrdenada) {
        resultado.add(actividad)
    }
    return resultado
}

fun buscarAnuncio(
    anuncios: ArrayList<Anuncio>,
    cadenaABuscar: String,
    umbral: Int = umbral()
): ArrayList<Anuncio> {
    //TODO
    return ArrayList()
}

fun buscarContacto(
    contactos: ArrayList<Contacto>,
    cadenaABuscar: String,
    umbral: Int = umbral()
): ArrayList<Contacto> {
    val lista = mutableListOf<Pair<Contacto, Int>>()
    for (contacto in contactos) {
        val distancia = calcularDistanciaLevenshtein(contacto.nombre, cadenaABuscar)
        if (distancia <= umbral)
            lista.add(contacto to distancia)
    }
    val listaOrdenada = lista.sortedBy { it.second }
    val resultado = ArrayList<Contacto>()
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