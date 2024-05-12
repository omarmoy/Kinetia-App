package com.dam2.proyectocliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.ui.screens.consumidor.ListaActividades
import com.dam2.proyectocliente.ui.menus.consumer.MenuBusqueda
import com.dam2.proyectocliente.ui.menus.ChatsMenu
import com.dam2.proyectocliente.ui.menus.consumer.MainMenu
import com.dam2.proyectocliente.ui.menus.consumer.MenuUsuario
import com.dam2.proyectocliente.ui.screens.consumidor.VistaActividad
import com.dam2.proyectocliente.ui.screens.consumidor.VistaAnuncio
import com.dam2.proyectocliente.ui.screens.Chat
import com.dam2.proyectocliente.ui.forms.FormActivity
import com.dam2.proyectocliente.ui.forms.FormularioAnuncio
import com.dam2.proyectocliente.ui.forms.EditActivity
import com.dam2.proyectocliente.ui.forms.ModificarAnuncio
import com.dam2.proyectocliente.ui.forms.SelectPicture
import com.dam2.proyectocliente.ui.inicio.ErrorScreen
import com.dam2.proyectocliente.ui.inicio.Inicio
import com.dam2.proyectocliente.ui.inicio.LoadingScreen
import com.dam2.proyectocliente.ui.inicio.Login
import com.dam2.proyectocliente.ui.menus.pro.MenuBusquedaAnuncio
import com.dam2.proyectocliente.ui.menus.pro.ReservationMenu
import com.dam2.proyectocliente.ui.menus.pro.MainMenuPro
import com.dam2.proyectocliente.ui.registro.AddImagen
import com.dam2.proyectocliente.ui.registro.ConfirmarRegistro
import com.dam2.proyectocliente.ui.registro.ElegirRol
import com.dam2.proyectocliente.ui.registro.ElegirTipoPro
import com.dam2.proyectocliente.ui.registro.NuevaEmpresaDatos
import com.dam2.proyectocliente.ui.registro.NuevoUsuarioDatos
import com.dam2.proyectocliente.ui.registro.NuevoUsuario
import com.dam2.proyectocliente.ui.screens.pro.ViewActivityPro
import com.dam2.proyectocliente.ui.screens.pro.VistaAnuncioPro
import com.dam2.proyectocliente.ui.screens.pro.ActivityReserves
import com.dam2.proyectocliente.utils.Picture
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegation(
    navController: NavHostController = rememberNavController(),
    vm: AppViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    Scaffold(
        topBar = {},
        content = { innerPadding -> Contenido(innerPadding, navController, vm, uiState) },
        bottomBar = {
            if (uiState.modoPro)
                PanelNavegacionPro(navController, vm, uiState)
            else
                PanelNavegacion(navController, vm, uiState)
        }
    )
}

@Composable
fun Contenido(
    innerPadding: PaddingValues, navController: NavHostController, vm: AppViewModel, uiState: UiState
) {
    NavHost(

        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screens.inicio.name
    ) {
        //Pantallas principales
        composable(route = Screens.menuPrincipal.name) {
        if(uiState.user != null) {
            when (vm.userUiState) {
                is UserUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is UserUiState.Success -> {
//                    vm.mostrarPanelNavegacion()
                    if (uiState.user.role == Role.PROVIDER && uiState.modoPro)
                        MainMenuPro(navController, vm, uiState)
                    else{
                        MainMenu(navController, vm, uiState)
                    }
                }

                is UserUiState.Error -> ErrorScreen(navController)
            }
        }else
            ErrorScreen(navController)

        }

        composable(route = Screens.menuBuscar.name) {
            MenuBusqueda(navController, vm, uiState)
        }

        composable(route = Screens.menuBusquedaDirecta.name) {
            MenuBusqueda(navController, vm, uiState, true)

        }

        composable(route = Screens.menuMensajes.name) {
            ChatsMenu(navController, vm, uiState)
        }

        composable(route = Screens.menuUsuario.name) {
            MenuUsuario(navController = navController, vm, uiState)
        }

        //SubPantallas
        composable(route = Screens.listaReservas.name) {
            //TODO: falta funcionalidad reservas
            ListaActividades(
                "Mis reservas",
                uiState.user!!.activitiesReserved,
                navController,
                vm,
                uiState
            )
        }
        composable(route = Screens.listaFavoritos.name) {
            ListaActividades(
                "Favoritos",
                uiState.user!!.activitiesFav,
                navController,
                vm,
                uiState
            )
        }
        composable(route = Screens.vistaActividad.name) {
            VistaActividad(navController, uiState.selectedActivity!!, vm, uiState)
        }
        composable(route = Screens.chat.name) {
            Chat(navController, uiState.chatSeleccionado, vm, uiState)
        }
        composable(route = Screens.vistaAnuncio.name) {
            VistaAnuncio(navController, uiState.advertisementSeleccionado, vm)
        }

        //Login
        composable(route = Screens.inicio.name) {
            Inicio(navController = navController)
        }
        composable(route = Screens.login.name) {
            Login(navController = navController, vm, uiState)
        }

        //Registro
        composable(route = Screens.elegirRol.name) {
            ElegirRol(navController = navController, vm)
        }
        composable(route = Screens.elegirTipoPro.name) {
            ElegirTipoPro(navController = navController, vm)
        }
        composable(route = Screens.nuevoUsuario.name) {
            NuevoUsuario(navController = navController, vm, uiState)
        }
        composable(route = Screens.nuevoUsuarioDatos.name) {
            NuevoUsuarioDatos(navController = navController, vm, uiState)
        }
        composable(route = Screens.nuevaEmpresaDatos.name) {
            NuevaEmpresaDatos(navController = navController, vm, uiState)
        }
        composable(route = Screens.addImagen.name) {
            AddImagen(navController = navController, vm, uiState)
        }
        composable(route = Screens.confirmarRegistro.name) {
            ConfirmarRegistro(navController = navController, vm, uiState)
        }

        //formularios y previstas Anuncio
        composable(route = Screens.formularioAnuncio.name) {
            FormularioAnuncio(navController = navController, vm, uiState)
        }
        composable(route = Screens.previewNuevoAnuncio.name) {
            VistaAnuncio(navController, uiState.nuevoAdvertisement!!, vm, true)
        }
        composable(route = Screens.modificarAnuncio.name) {
            ModificarAnuncio(navController, vm, uiState, uiState.modAdvertisement!!)
        }

        //formularios y previstas ACTIVIDAD
        composable(route = Screens.formularioActividad.name) {
            FormActivity(navController = navController, vm, uiState)
        }
        composable(route = Screens.previewNuevaActividad.name) {
            ViewActivityPro(navController, uiState.newActivity!!, vm, true)
        }
        composable(route = Screens.vistaActividadPro.name) {
            ViewActivityPro(navController, uiState.selectedActivity!!, vm)
        }

        composable(route = Screens.modificarActividad.name) {
            EditActivity(navController, vm, uiState, uiState.modActivity!!)
        }

        //Menus Pro
        composable(route = Screens.menuPrincipalPro.name) {
            MainMenuPro(navController, vm, uiState)
        }
        composable(route = Screens.menuBusquedaAnuncios.name) {
            MenuBusquedaAnuncio(navController, vm, uiState)
        }
        composable(route = Screens.vistaAnuncioPro.name) {
            VistaAnuncioPro(navController, uiState.advertisementSeleccionado, vm)
        }
        composable(route = Screens.menuReservas.name) {
            ReservationMenu(navController, vm, uiState)
        }
        composable(route = Screens.vistaReservasActividad.name) {
            ActivityReserves(navController, vm, uiState.selectedActivity!!)
        }

        //Selectors Pictures
        composable(route = Screens.selectActivityPicture.name) {
            SelectPicture(navController, vm, Picture.activityPictures)
        }
        composable(route = Screens.selectProfilePicture.name) {
            SelectPicture(navController, vm, Picture.profilePictures)
        }

    }
}

@Composable
fun PanelNavegacion(navController: NavHostController, vm: AppViewModel, estado: UiState) {
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
                IconButton(onClick = {
                    vm.cambiarBotonNav(0)
                    vm.setIndiceCategoria()
                    navController.navigate(Screens.menuPrincipal.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Inicio",
                        tint = if (estado.botoneraNav[0]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(1)
                    vm.selectCategoria(Category.TODO)
                    navController.navigate(Screens.menuBuscar.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = if (estado.botoneraNav[1]) AmarilloPastel else NegroClaro
                    )
                }

                IconButton(onClick = {
                    vm.cambiarBotonNav(2)
                    navController.navigate(Screens.menuMensajes.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = if (estado.botoneraNav[2]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(3)
                    navController.navigate(Screens.menuUsuario.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Mi Cuenta",
                        tint = if (estado.botoneraNav[3]) AmarilloPastel else NegroClaro
                    )
                }
            }
        }
    }
}

@Composable
fun PanelNavegacionPro(navController: NavHostController, vm: AppViewModel, estado: UiState) {
//    if (true) {
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
                IconButton(onClick = {
                    vm.cambiarBotonNav(0)
                    navController.navigate(Screens.menuPrincipalPro.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú principal Pro",
                        tint = if (estado.botoneraNav[0]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(1)
                    navController.navigate(Screens.menuReservas.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Reservas",
                        tint = if (estado.botoneraNav[1]) AmarilloPastel else NegroClaro
                    )
                }

                IconButton(onClick = {
                    vm.cambiarBotonNav(2)
                    navController.navigate(Screens.menuMensajes.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = if (estado.botoneraNav[2]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(3)
                    navController.navigate(Screens.menuBusquedaAnuncios.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "buscar",
                        tint = if (estado.botoneraNav[3]) AmarilloPastel else NegroClaro
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BarraPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    PanelNavegacionPro(navController, vm, estado)
}