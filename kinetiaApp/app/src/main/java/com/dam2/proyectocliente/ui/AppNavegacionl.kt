package com.dam2.proyectocliente.ui

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
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Categoria
import com.dam2.proyectocliente.ui.vistas.consumidor.ListaActividades
import com.dam2.proyectocliente.ui.menus.consumidor.MenuBusqueda
import com.dam2.proyectocliente.ui.menus.MenuConversaciones
import com.dam2.proyectocliente.ui.menus.consumidor.MenuPrincipal
import com.dam2.proyectocliente.ui.menus.consumidor.MenuUsuario
import com.dam2.proyectocliente.ui.vistas.consumidor.VistaActividad
import com.dam2.proyectocliente.ui.vistas.consumidor.VistaAnuncio
import com.dam2.proyectocliente.ui.vistas.VistaChat
import com.dam2.proyectocliente.ui.formularios.FormularioActividad
import com.dam2.proyectocliente.ui.formularios.FormularioAnuncio
import com.dam2.proyectocliente.ui.formularios.ModificarActividad
import com.dam2.proyectocliente.ui.formularios.ModificarAnuncio
import com.dam2.proyectocliente.ui.inicio.Inicio
import com.dam2.proyectocliente.ui.inicio.Login
import com.dam2.proyectocliente.ui.menus.pro.MenuBusquedaAnuncio
import com.dam2.proyectocliente.ui.menus.pro.MenuReservas
import com.dam2.proyectocliente.ui.menus.pro.MenuPrincipalPro
import com.dam2.proyectocliente.ui.registro.AddImagen
import com.dam2.proyectocliente.ui.registro.ConfirmarRegistro
import com.dam2.proyectocliente.ui.registro.ElegirRol
import com.dam2.proyectocliente.ui.registro.ElegirTipoPro
import com.dam2.proyectocliente.ui.registro.NuevaEmpresaDatos
import com.dam2.proyectocliente.ui.registro.NuevoUsuarioDatos
import com.dam2.proyectocliente.ui.registro.NuevoUsuario
import com.dam2.proyectocliente.ui.vistas.pro.VistaActividadPro
import com.dam2.proyectocliente.ui.vistas.pro.VistaAnuncioPro
import com.dam2.proyectocliente.ui.vistas.pro.VistaReservas
import com.example.proyectocliente.ui.theme.AmarilloPastel
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
        bottomBar = {
            if (estado.modoPro)
                PanelNavegacionPro(navController, vm, estado)
            else
                PanelNavegacion(navController, vm, estado)
        }
    )
}

@Composable
fun Contenido(
    innerPadding: PaddingValues, navController: NavHostController, vm: AppViewModel, estado: UiState
) {
    NavHost(

        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination =
        if (estado.usuario == null) //TODO: cambiar condición
            Pantallas.inicio.name
        else if (estado.modoPro) //TODO: cambiar condición
            Pantallas.menuPrincipalPro.name
        else
            Pantallas.menuPrincipal.name
    ) {
        //Pantallas principales
        composable(route = Pantallas.menuPrincipal.name) {
            MenuPrincipal(navController, vm, estado)
        }

        composable(route = Pantallas.menuBuscar.name) {
            MenuBusqueda(navController, vm, estado)
        }

        composable(route = Pantallas.menuBusquedaDirecta.name) {
            MenuBusqueda(navController, vm, estado, true)

        }

        composable(route = Pantallas.menuMensajes.name) {
            MenuConversaciones(navController, vm, estado)
        }

        composable(route = Pantallas.menuUsuario.name) {
            MenuUsuario(navController = navController, vm, estado)
        }

        //SubPantallas
        composable(route = Pantallas.listaReservas.name) {
            //TODO: falta funcionalidad reservas
            ListaActividades(
                "Mis reservas",
                estado.usuario!!.actividadesReservadas,
                navController,
                vm,
                estado
            )
        }
        composable(route = Pantallas.listaFavoritos.name) {
            ListaActividades(
                "Favoritos",
                estado.usuario!!.actividadesFav,
                navController,
                vm,
                estado
            )
        }
        composable(route = Pantallas.vistaActividad.name) {
            VistaActividad(navController, estado.actividadSeleccionada, vm, estado)
        }
        composable(route = Pantallas.chat.name) {
            VistaChat(navController, estado.contactoSeleccionado, vm, estado)
        }
        composable(route = Pantallas.vistaAnuncio.name) {
            VistaAnuncio(navController, estado.anuncioSeleccionado, vm)
        }

        //Login
        composable(route = Pantallas.inicio.name) {
            Inicio(navController = navController)
        }
        composable(route = Pantallas.login.name) {
            Login(navController = navController, vm, estado)
        }

        //Registro
        composable(route = Pantallas.elegirRol.name) {
            ElegirRol(navController = navController, vm)
        }
        composable(route = Pantallas.elegirTipoPro.name) {
            ElegirTipoPro(navController = navController, vm)
        }
        composable(route = Pantallas.nuevoUsuario.name) {
            NuevoUsuario(navController = navController, vm, estado)
        }
        composable(route = Pantallas.nuevoUsuarioDatos.name) {
            NuevoUsuarioDatos(navController = navController, vm, estado)
        }
        composable(route = Pantallas.nuevaEmpresaDatos.name) {
            NuevaEmpresaDatos(navController = navController, vm, estado)
        }
        composable(route = Pantallas.addImagen.name) {
            AddImagen(navController = navController, vm, estado)
        }
        composable(route = Pantallas.confirmarRegistro.name) {
            ConfirmarRegistro(navController = navController, vm, estado)
        }

        //formularios y previstas Anuncio
        composable(route = Pantallas.formularioAnuncio.name) {
            FormularioAnuncio(navController = navController, vm, estado)
        }
        composable(route = Pantallas.previewNuevoAnuncio.name) {
            VistaAnuncio(navController, estado.nuevoAnuncio!!, vm, true)
        }
        composable(route = Pantallas.modificarAnuncio.name) {
            ModificarAnuncio(navController, vm, estado, estado.modAnuncio!!)
        }

        //formularios y previstas ACTIVIDAD
        composable(route = Pantallas.formularioActividad.name) {
            FormularioActividad(navController = navController, vm)
        }
        composable(route = Pantallas.previewNuevaActividad.name) {
            VistaActividadPro(navController, estado.nuevaActividad!!, vm, estado, true)
        }
        composable(route = Pantallas.vistaActividadPro.name) {
            VistaActividadPro(navController, estado.actividadSeleccionada, vm, estado)
        }

        composable(route = Pantallas.modificarActividad.name) {
            ModificarActividad(navController, vm, estado, estado.modActividad!!)
        }

        //Menus Pro
        composable(route = Pantallas.menuPrincipalPro.name) {
            MenuPrincipalPro(navController, vm, estado)
        }
        composable(route = Pantallas.menuBusquedaAnuncios.name) {
            MenuBusquedaAnuncio(navController, vm, estado)
        }
        composable(route = Pantallas.vistaAnuncioPro.name) {
            VistaAnuncioPro(navController, estado.anuncioSeleccionado, vm)
        }
        composable(route = Pantallas.menuReservas.name) {
            MenuReservas(navController, vm, estado)
        }
        composable(route = Pantallas.vistaReservasActividad.name) {
            VistaReservas(navController, vm, estado.actividadSeleccionada)
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
                    navController.navigate(Pantallas.menuPrincipal.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Inicio",
                        tint = if (estado.botoneraNav[0]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(1)
                    vm.selectCategoria(Categoria.TODO)
                    navController.navigate(Pantallas.menuBuscar.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = if (estado.botoneraNav[1]) AmarilloPastel else NegroClaro
                    )
                }

                IconButton(onClick = {
                    vm.cambiarBotonNav(2)
                    navController.navigate(Pantallas.menuMensajes.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = if (estado.botoneraNav[2]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(3)
                    navController.navigate(Pantallas.menuUsuario.name)
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
                    navController.navigate(Pantallas.menuPrincipalPro.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú principal Pro",
                        tint = if (estado.botoneraNav[0]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(1)
                    navController.navigate(Pantallas.menuReservas.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Reservas",
                        tint = if (estado.botoneraNav[1]) AmarilloPastel else NegroClaro
                    )
                }

                IconButton(onClick = {
                    vm.cambiarBotonNav(2)
                    navController.navigate(Pantallas.menuMensajes.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = if (estado.botoneraNav[2]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(3)
                    navController.navigate(Pantallas.menuBusquedaAnuncios.name)
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