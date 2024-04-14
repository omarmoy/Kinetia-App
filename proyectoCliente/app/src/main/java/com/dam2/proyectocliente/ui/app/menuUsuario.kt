package com.dam2.proyectocliente.ui.app

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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Rol
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.BlancoFondOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.pequena

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuUsuario(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    Scaffold(
        topBar = { BarraSuperiorPerfil(navController = navController, vm, estado) },
        content = { innerPadding -> ContenidoUsuario(innerPadding, navController, vm, estado) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorPerfil(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var mostrarMenu by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
        title = { /*sin título*/ },
        actions = {
            IconButton(onClick = {
                if (estado.usuario.tieneMensajesSinLeer()) {
                    vm.filtrarMensajesNoleidos()
                    vm.cambiarBotonNav(2)
                    navController.navigate(Pantallas.menuMensajes.name)
                } else {
                    //TODO dialogo emergente "No tiene mensajes nuevos"
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "notificacion",
                    tint = if (estado.usuario.tieneMensajesSinLeer()) Rojo else AzulAguaOscuro
                )
            }
            //Spacer(modifier = Modifier.width(12.dp))

            //Ajustes
            IconButton(onClick = { mostrarMenu = !mostrarMenu }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Ajustes",
                    tint = AzulAguaOscuro
                )
            }
            DropdownMenu(
                expanded = mostrarMenu,
                onDismissRequest = { mostrarMenu = false },
                modifier = Modifier.background(BlancoFondo)
            ) {
                if (estado.usuario.rol == Rol.OFERTANTE || estado.usuario.rol == Rol.ADMINISTRADOR) {
                    DropdownMenuItem(
                        text = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (estado.modoPro)
                                    Text(text = "Modo Ofertante")
                                else
                                    Text(text = "Modo Consumidor")
                                Icon(
                                    imageVector = Icons.Filled.Refresh,
                                    contentDescription = "modo"
                                )
                            }
                        },
                        onClick = {
                            /*TODO*/
                            vm.cambiarModo()
                        })
                }
                DropdownMenuItem(
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Editar perfil")
                            Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "edit")
                        }
                    },
                    onClick = {
                        /*TODO*/
                    })
                DropdownMenuItem(
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Cerrar sesión")
                            Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "salir")
                        }
                    },
                    onClick = {
                        /*TODO*/
                        vm.ocultarPanelNavegacion()
                        vm.cambiarBotonNav(0)
                        navController.navigate(Pantallas.inicio.name)
                    })
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoUsuario(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState
) {
    var verPerfil by rememberSaveable { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddinHijo ->
            Column(
                modifier = Modifier
                    .padding(paddinHijo)
                //.background(BlancoFondo)  //blanco por defecto
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                    //.padding(bottom = 0.dp)
                    //.background(AzulFondo)
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
                            text = "Anuncios",
                            color = if (!verPerfil) Color.Black else Color.White
                        )
                    }
                }

                LazyColumn {
                    if (verPerfil)
                        item { PanelPerfil(navController, vm, estado) }
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
                                /*IconButton(onClick = { TODO() }) {
                                    Icon(
                                        imageVector = Icons.Filled.AddCircle,
                                        contentDescription = "nuevoAnuncio",
                                        tint = AzulLogo
                                    )
                                }*/
                            }
                        }
                        //TODO("controlar cuando no haya publicado ningún anuncio")
                        items(estado.usuario.anunciosPublicados) { anuncio ->
                            MiniaturaAnuncio(anuncio, navController, vm)
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
                        //TODO: añadir anuncio
                    }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = "Publicar anuncio",
                                tint = AzulAguaOscuro
                            )
                            Text(text = "Publicar", fontSize = pequena)
                        }

                    }
                }
            }
        }
    )


}


@Composable
fun PanelPerfil(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    DatosPerfil(vm, estado)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        //Text(text = "Mis reservas", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Titulo(texto = "Mis reservas")
        OutlinedButton(
            onClick = { navController.navigate(Pantallas.listaReservas.name) },
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
        items(DatosPrueba.actividades /*TODO(falta filtro)*/) { a ->
            MiniaturaScrollLateral(
                a,
                vm,
                navController,
                estado,
                true
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        //Text(text = "Favoritos", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Titulo(texto = "Favoritos")
        OutlinedButton(
            onClick = { navController.navigate(Pantallas.listaFavoritos.name) },
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
        items(estado.usuario.actividadesFav) { a ->
            MiniaturaScrollLateral(
                a,
                vm,
                navController,
                estado,
                true
            )
        }
    }
}

@Composable
fun DatosPerfil(vm: AppViewModel, estado: UiState) {
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
                painter = painterResource(id = estado.usuario.foto),
                contentDescription = estado.usuario.nombre,
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
                text = estado.usuario.nombre + " " + estado.usuario.apellido1 + " " + estado.usuario.apellido2,
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadPerfil(a: Actividad) {
    val tam = 180.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RectangleShape,
            onClick = {/*TODO*/ }) {
            Image(
                painter = painterResource(id = a.imagen),
                contentDescription = a.titulo,
                modifier = Modifier
                    //.fillMaxHeight()
                    .width(tam),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            colors = CardDefaults.cardColors(AzulFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
                .width(tam)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = a.titulo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaAnuncio(anuncio: Anuncio, navController: NavHostController, vm: AppViewModel) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
            .height(60.dp)
    ) {
        Card(
            onClick = {
                vm.ocultarPanelNavegacion()
                vm.selectAnuncio(anuncio)
                navController.navigate(Pantallas.vistaAnuncio.name)
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
                Text(text = anuncio.titulo)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = anuncio.fecha.mostrarFecha(), fontSize = 12.sp)
                    IconButton(onClick = {
                        // TODO:(borrar anuncio)
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
fun PerfilPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = { BarraSuperiorPerfil(navController, vm, estado) },
        content = { innerPadding -> ContenidoUsuario(innerPadding, navController, vm, estado) },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacion(navController = navController, vm, estado) }
    )
}