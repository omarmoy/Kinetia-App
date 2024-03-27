package com.dam2.proyectocliente.ui.app

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Principal(
    navController: NavHostController = rememberNavController(),
    vm: AppViewModel = viewModel()
) {
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = {},
        content = { innerPadding -> Contenido(innerPadding, navController, vm, estado) },
        bottomBar = { PanelNavegacion(navController, estado) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Contenido(
    innerPadding: PaddingValues, navController: NavHostController, vm: AppViewModel, estado: UiState
) {
    NavHost(
        navController = navController,
        startDestination = Pantallas.menuPrincipal.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        //Pantallas principales
        composable(route = Pantallas.menuPrincipal.name) {
            MenuPrincipal(navController, vm, estado)
        }

        composable(route = Pantallas.menuBuscar.name) {
            MenuBusqueda(navController = navController)
        }

        composable(route = Pantallas.menuMensajes.name) {
            MenuConversaciones( navController, vm, estado)
        }

        composable(route = Pantallas.menuMiPerfil.name) {
            MenuUsuario(navController = navController, vm, estado)
        }

        //SubPantallas
        composable(route = Pantallas.vistaActividad.name) {
            VistaActividad(navController, estado.actividadSeleccionada, vm)
        }
        composable(route = Pantallas.chat.name){
            VistaChat(navController, estado.contactoSeleccionado , vm, estado)
        }
        composable(route = Pantallas.vistaAnuncio.name){
            //TODO
        }

    }
}

@Composable
fun PanelNavegacion(navController: NavHostController, estado: UiState) {
    if (estado.mostrarPanelNavegacion) {
        Box(
            modifier = Modifier
                .background(Gris2)
                .padding(top = 1.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(BlancoFondo)
                    .padding(12.dp)
            ) {
                IconButton(onClick = { navController.navigate(Pantallas.menuPrincipal.name) }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Inicio",
                        tint = NegroClaro
                    )
                }
                IconButton(onClick = { navController.navigate(Pantallas.menuBuscar.name) }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = NegroClaro
                    )
                }

                IconButton(onClick = { navController.navigate(Pantallas.menuMensajes.name) }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = NegroClaro
                    )
                }
                IconButton(onClick = { navController.navigate(Pantallas.menuMiPerfil.name) }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Mi Cuenta",
                        tint = NegroClaro
                    )
                }
            }
        }
    }
}