package com.dam2.proyectocliente.utils

/**
 * Este código buscará cadenas en la lista listaDeStrings que tengan una distancia de
 * Levenshtein menor o igual al umbral especificado (en este caso, 2)
 */

fun algoritmoLevenshtein() {
    val listaDeStrings = arrayListOf("manzana", "banana", "pera", "kiwi", "mango")

    val cadenaDeBusqueda = "manza"

    val resultados = buscarCadenasAproximadas(listaDeStrings, cadenaDeBusqueda, 2)

    println("Resultados de búsqueda:")
    resultados.forEach { println(it) }
}

fun buscarCadenasAproximadas(lista: ArrayList<String>, cadena: String, umbral: Int): List<String> {
    val resultados = ArrayList<String>()

    lista.forEach { str ->
        if (calcularDistanciaLevenshtein_0(str, cadena) <= umbral) {
            resultados.add(str)
        }
    }

    return resultados
}

fun calcularDistanciaLevenshtein_0(s1: String, s2: String): Int {
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



