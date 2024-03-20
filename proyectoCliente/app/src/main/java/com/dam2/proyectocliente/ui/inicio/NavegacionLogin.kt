package com.dam2.proyectocliente.ui.inicio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.ui.Pantallas

@Composable
fun NavegacionInicial(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Pantallas.inicio.name
    ) {

        composable(route = Pantallas.inicio.name) {
            Inicio(navController = navController)
        }

        composable(route = Pantallas.login.name){
            Login (navController = navController)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavegacionApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val pantallaActual = Pantallas.valueOf(
        backStackEntry?.destination?.route ?: Pantallas.menuPrincipal.name
    )
    Scaffold(
        topBar = {
            Cabecera(
                hayPantallaAnterior = navController.previousBackStackEntry != null
                        && pantallaActual != Pantallas.menuPrincipal,
                navegarAtras = { navController.navigateUp() }
            )
        },
        content = { padding -> Contenido(padding, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cabecera(
    hayPantallaAnterior: Boolean,
    navegarAtras: () -> Unit
) {
    //CenterAlignedTopAppBar
    TopAppBar(
        title = { },
        navigationIcon = {
            if (hayPantallaAnterior) {
                IconButton(onClick = navegarAtras) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "atras",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}

@Composable
fun Contenido(padding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Pantallas.menuPrincipal.name,
        modifier = Modifier.padding(padding)
    ) {

        composable(route = Pantallas.menuPrincipal.name) {
            //TODO
        }

    }
}