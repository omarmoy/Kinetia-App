package com.dam2.proyectocliente.ui.menus

import androidx.compose.foundation.background
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
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.models.Screens
import com.example.proyectocliente.ui.theme.BlancoFondo

@Composable
fun DropdownConfig(
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
        if (estado.user!!.role == Role.PROVIDER || estado.user.role == Role.ADMIN) {
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
                    if (vm.changeMode())
                        navController.navigate(Screens.menuPrincipalPro.name)
                    else
                        navController.navigate(Screens.menuPrincipal.name)
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
                vm.changeMode()
                vm.ocultarPanelNavegacion()
                vm.cambiarBotonNav(0)
                navController.navigate(Screens.inicio.name)
            })
    }
}