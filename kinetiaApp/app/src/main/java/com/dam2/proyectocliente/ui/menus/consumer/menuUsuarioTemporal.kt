package com.dam2.proyectocliente.ui.menus.consumer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.PanelNavegacion
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.menus.DropdownConfig
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.small

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuUsuario2(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var borrarAdvertisement by remember { mutableStateOf<Advertisement?>(null) }
    val setBorrarAnuncio: (Advertisement?) -> Unit = { anuncio -> borrarAdvertisement = anuncio }

    Scaffold(
        topBar = {
            BarraSuperiorPerfil2(
                navController = navController,
                vm,
                estado
            )
        },
        content = { innerPadding ->
            ContenidoUsuario2(
                innerPadding,
                navController,
                vm,
                estado,
                setBorrarAnuncio
            )
        }
    )

    if (borrarAdvertisement != null) {
        DialogInfo(
            onDismissRequest = { setBorrarAnuncio(null) },
            onConfirmation = { vm.deleteAdvertisement(borrarAdvertisement!!); setBorrarAnuncio(null) },
            dialogTitle = borrarAdvertisement!!.title,
            dialogText = "¿Quieres borrar este anunco?",
            buttonConfirm = "Aceptar",
            buttonDismiss = "Cancelar"
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorPerfil2(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var mostrarMenu by remember { mutableStateOf(false) }
//    val setMostrarMenu: (Boolean) -> Unit = { value -> mostrarMenu = value }
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
        title = { /*sin título*/ },
        actions = {
            IconButton(onClick = {
                if (estado.user!!.tieneMensajesSinLeer()) {
                    vm.filtrarMensajesNoleidos()
                    vm.cambiarBotonNav(2)
                    navController.navigate(Screens.menuMensajes.name)
                } else {
                    //TODO dialogo emergente "No tiene mensajes nuevos"
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "notificacion",
                    tint = if (estado.user!!.tieneMensajesSinLeer()) Rojo else AzulAguaOscuro
                )
            }

            //Ajustes
            IconButton(onClick = { mostrarMenu = !mostrarMenu }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Ajustes",
                    tint = AzulAguaOscuro
                )
            }

            DropdownConfig(navController, vm, estado, mostrarMenu) { mostrarMenu = false }


        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoUsuario2(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState,
    setBorrarAnuncio: (Advertisement) -> Unit
) {
    var verPerfil by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddinHijo ->
            Column(
                modifier = Modifier
                    .padding(paddinHijo)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                ) {
                    Button(
                        onClick = { verPerfil = true },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (verPerfil) AzulAguaClaro else AzulAguaOscuro
                        ),
                        contentPadding = PaddingValues(8.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Perfil",
                            color = if (verPerfil) NegroClaro else Color.White
                        )
                    }
                    Button(
                        onClick = { verPerfil = false },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!verPerfil) AzulAguaClaro else AzulAguaOscuro
                        ),
                        contentPadding = PaddingValues(8.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Mis anuncios",
                            color = if (!verPerfil) Color.Black else Color.White
                        )
                    }
                }

                LazyColumn {
                    if (verPerfil)
                        item {
                            PanelPerfil2(
                                navController,
                                vm,
                                estado
                            )
                        }
                    else {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp)
                                    .height(75.dp)
                                    .background(AzulAguaClaro)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Publicados:",
                                    fontSize = 18.sp
                                )
                            }
                        }
                        items(estado.user!!.advertisements) { anuncio ->
                            Anuncio(
                                anuncio,
                                navController,
                                vm,
                                setBorrarAnuncio
                            )
                        }

                        if (estado.user.advertisements.size == 0) {
                            item {
                                Text(
                                    text = "No ha publicado ningún anuncio todavía",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 100.dp)
                                )
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            if (!verPerfil) {
                Surface(
                    shape = CircleShape,
                ) {
                    IconButton(onClick = {
                        vm.ocultarPanelNavegacion()
                        navController.navigate(Screens.formularioAnuncio.name)
                    }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = "Publicar anuncio",
                                tint = AzulAguaOscuro
                            )
                            Text(text = "Publicar", fontSize = small)
                        }

                    }
                }
            }
        }
    )


}


@Composable
fun PanelPerfil2(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    DatosPerfil2(estado)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Title(texto = "Mis reservas")
        OutlinedButton(
            onClick = { navController.navigate(Screens.listaReservas.name) },
            modifier = Modifier
                .height(30.dp)
                .width(75.dp),
            contentPadding = PaddingValues(0.dp),
            //colors= ButtonColors,
            border = BorderStroke(1.dp, BlancoFondOscuro)
        ) {
            Text(
                text = "Ver todo", fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = NegroClaro
            )
        }
    }
    LazyRow {
        items(estado.user!!.activitiesReserved) { a ->
            ActivityScrollLateral(
                a,
                vm,
                navController,
                estado,
                true
            )
        }
        if (estado.user.activitiesReserved.size == 0) {
            item {
                Text(
                    text = "No ha reservado ninguna actividad",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp)
                )
            }
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Title(texto = "Favoritos")
        OutlinedButton(
            onClick = { navController.navigate(Screens.listaFavoritos.name) },
            modifier = Modifier
                .height(30.dp)
                .width(75.dp),
            contentPadding = PaddingValues(0.dp),
            border = BorderStroke(1.dp, BlancoFondOscuro)
        ) {
            Text(
                text = "Ver todo", fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = NegroClaro
            )
        }
    }
    LazyRow {
        items(estado.user!!.activitiesFav) { a ->
            ActivityScrollLateral(
                a,
                vm,
                navController,
                estado,
                true
            )
        }
        if (estado.user.activitiesFav.size == 0) {
            item {
                Text(
                    text = "No ha marcado ninguna actividad como favorita",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp)
                )
            }
        }
    }
}

@Composable
fun DatosPerfil2(estado: UiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height(75.dp)
            .background(AzulAguaClaro)
    ) {
        Card(shape = CircleShape) {
            Image(
                painter = painterResource(id = R.drawable.nofoto),
                contentDescription = estado.user!!.name,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = estado.user!!.fullName(),
                fontSize = 18.sp
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anuncio(
    advertisement: Advertisement,
    navController: NavHostController,
    vm: AppViewModel,
    setBorrarAnuncio: (Advertisement) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
            .height(60.dp)
    ) {
        Card(
            onClick = {
                vm.ocultarPanelNavegacion()
                vm.selectAnuncio(advertisement)
                navController.navigate(Screens.vistaAnuncio.name)
            },
            shape = RectangleShape
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlancoFondo)
                    .padding(8.dp)
            ) {
                Text(text = advertisement.title)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = advertisement.creationDate.toString(), fontSize = 12.sp)
                    IconButton(onClick = {
                        setBorrarAnuncio(advertisement)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Borrar",
                            tint = NegroClaro
                        )
                    }

                }
            }

        }
    }
}


/**
 * VISTA PREVIA
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfilePasdfreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    val setBorrarAnuncio: (Advertisement) -> Unit = { }
    Scaffold(
        topBar = {
            BarraSuperiorPerfil2(
                navController,
                vm,
                estado
            )
        },
        content = { innerPadding ->
            ContenidoUsuario2(
                innerPadding,
                navController,
                vm,
                estado,
                setBorrarAnuncio
            )
        },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacion(navController = navController, vm, estado) }
    )
}