package com.dam2.proyectocliente.controlador

import androidx.compose.runtime.LaunchedEffect

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
        if (calcularDistanciaLevenshtein(str, cadena) <= umbral) {
            resultados.add(str)
        }
    }

    return resultados
}

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
            val substitution = if (s1[i - 1] == s2[j - 1]) cost[i - 1][j - 1] else cost[i - 1][j - 1] + 1
            cost[i][j] = minOf(deletion, insertion, substitution)
        }
    }

    return cost[len0 - 1][len1 - 1]
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaScrollLateral(
    a: Actividad,
    vm: AppViewModel,
    navController: NavHostController
) {
    val tam = 230.dp

    // Obtener el estado de favoritos del ViewModel
    val favorito = vm.esFavorita(a)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        Card(
            onClick = {
                vm.selectActividad(a)
                vm.ocultarPanelNavegacion()
                navController.navigate(Pantallas.vistaActividad.name)
            }
        ) {
            Image(
                painter = painterResource(id = a.imagen),
                contentDescription = a.titulo,
                modifier = Modifier
                    .width(tam)
                    .height(tam * 2 / 3),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            colors = CardDefaults.cardColors(AzulAguaFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
                .width(tam)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.widthIn(max = tam * 8 / 10)) {
                    Text(
                        text = a.titulo,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text = a.ubicacion ?: "", fontSize = pequena)
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(0.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    IconButton(onClick = {
                        if (favorito)
                            vm.eliminarFavorito(a)
                        else
                            vm.addFavorito(a)
                    }) {
                        Icon(
                            imageVector = if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Fav",
                            tint = if (favorito) AmarilloPastel else NegroClaro
                        )
                    }
                }
            }
        }
    }

    // Observar cambios en el estado de favoritos y forzar una recomposición del LazyRow
    LaunchedEffect(favorito) {
        vm.refresh() // Método en tu ViewModel para actualizar los datos
    }
}

 */

