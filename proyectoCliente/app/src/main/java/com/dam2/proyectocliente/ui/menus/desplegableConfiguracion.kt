package com.dam2.proyectocliente.ui.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Rol
import com.dam2.proyectocliente.ui.PanelNavegacionPro
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.BlancoFondo

@Composable
fun DesplegableConfiguarion(
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState,
    mostrarMenu: Boolean,
    onDismissRequest: () -> Unit,
) {
    DropdownMenu(
        expanded = mostrarMenu,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(BlancoFondo)
    ) {
        if (estado.usuario!!.rol == Rol.OFERTANTE || estado.usuario.rol == Rol.ADMINISTRADOR) {
            DropdownMenuItem(
                text = {
                    if (estado.modoPro)
                        Text(text = "Modo Consumidor")
                    else
                        Text(text = "Modo Ofertante")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "modo")
                },
                onClick = {
                    if (vm.cambiarModo())
                        navController.navigate(Pantallas.menuPrincipalPro.name)
                    else
                        navController.navigate(Pantallas.menuPrincipal.name)
                })
        }
        DropdownMenuItem(
            text = { Text(text = "Editar perfil") },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "edit")
            },
            onClick = {
                /*TODO: editar perfil*/
            })
        DropdownMenuItem(
            text = { Text(text = "Cerrar sesión") },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "salir")
            },
            onClick = {
                /*TODO borrar datos usuario*/
                vm.ocultarPanelNavegacion()
                vm.cambiarBotonNav(0)
                navController.navigate(Pantallas.inicio.name)
            })
    }
}