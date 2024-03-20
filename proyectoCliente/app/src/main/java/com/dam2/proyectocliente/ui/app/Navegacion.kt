package com.dam2.proyectocliente.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Principal(navController: NavHostController=rememberNavController()){
    Scaffold (
        topBar = {},
        content = {innerPadding ->Contenido(innerPadding, navController)},
        bottomBar = { PanelNavegacion(navController = navController)}
    )
}


@Composable
fun BarraSuperior(navController: NavHostController){

}

@Composable
fun Contenido(innerPadding: PaddingValues, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Pantallas.menuPrincipal.name,
        modifier = Modifier.padding(innerPadding)
    ) {

        composable(route = Pantallas.menuPrincipal.name) {
            MenuPrincipal(navController = navController)
        }

        composable(route = Pantallas.menuBuscar.name){
            MenuBusqueda(navController = navController)
        }

        composable(route = Pantallas.menuMensajes.name){
            MenuConversaciones(navController = navController)
        }

        composable(route = Pantallas.menuMiPerfil.name){
            //TODO
        }
    }
}

@Composable
fun PanelNavegacion(navController: NavHostController){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFF4F4F4))
            .padding(12.dp)
    ) {
        IconButton(onClick = { navController.navigate(Pantallas.menuPrincipal.name)}) {
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

        IconButton (onClick = { navController.navigate(Pantallas.menuMensajes.name) }) {
            Icon(
                imageVector = Icons.Filled.MailOutline,
                contentDescription = "Mensajes",
                tint = NegroClaro
            )
        }
        IconButton (onClick = { navController.navigate(Pantallas.menuMiPerfil.name) }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Mi Cuenta",
                tint = NegroClaro
            )
        }
    }
}