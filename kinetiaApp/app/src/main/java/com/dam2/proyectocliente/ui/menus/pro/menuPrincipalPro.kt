package com.dam2.proyectocliente.ui.menus.pro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.ui.PanelNavegacionPro
import com.dam2.proyectocliente.ui.Pantallas
import com.dam2.proyectocliente.ui.menus.DesplegableConfiguarion
import com.dam2.proyectocliente.ui.menus.consumidor.Titulo
import com.dam2.proyectocliente.ui.recursos.DialogoInfo
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.pequena

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipalPro(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var borrarActividad by remember { mutableStateOf<Actividad?>(null) }
    val setBorrarActividad: (Actividad?) -> Unit = { actividad -> borrarActividad = actividad }

    Scaffold(
        topBar = { BarraSuperiorPro(navController, vm, estado) },
        content = { innerPadding ->
            ContenidoPrincipalPro(innerPadding, navController, vm, estado, setBorrarActividad)
        }
    )

    if (borrarActividad != null) {
        DialogoInfo(
            onDismissRequest = { setBorrarActividad(null) },
            onConfirmation = { vm.borrarActividad(borrarActividad!!); setBorrarActividad(null) },
            dialogTitle = borrarActividad!!.titulo,
            dialogText = "¿Quieres borrar este actividad?",
            buttonConfirm = "Aceptar",
            buttonDismiss = "Cancelar"
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorPro(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    var mostrarMenu by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
        title = {
            Image(
                painter = painterResource(R.drawable.logolinealetraylogo),
                contentDescription = "logo",
                modifier = Modifier.height(35.dp)
            )
        },
        actions = {
            IconButton(onClick = {
                if (estado.usuario!!.tieneMensajesSinLeer()) {
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
                    tint = if (estado.usuario!!.tieneMensajesSinLeer()) Rojo else AzulAguaOscuro
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

            DesplegableConfiguarion(navController, vm, estado, mostrarMenu) { mostrarMenu = false }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoPrincipalPro(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState,
    setBorrarActividad: (Actividad?) -> Unit
) {

    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddinHijo ->
            Column(modifier = Modifier.padding(paddinHijo)) {
                DatosPerfilPro(estado)
                LazyColumn(modifier = Modifier.padding(8.dp)) {

                    item { Titulo(texto = "Mis Actividades") }

                    if (estado.usuario!!.actividadesOfertadas.size == 0) {
                        item {
                            Text(
                                text = "Todavía no has publicado ninguna actividad",
                                modifier = Modifier.padding(top = 40.dp)
                            )
                        }
                    } else {
                        items(estado.usuario!!.actividadesOfertadas) { actividad ->
//                    items(DatosPrueba.actividades) { actividad -> //TODO quitar
                            MiniaturaActividadOfertada(
                                actividad, vm, navController, estado, setBorrarActividad
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            Surface(
                shape = CircleShape,
            ) {
                IconButton(onClick = {
                    vm.ocultarPanelNavegacion()
                    navController.navigate(Pantallas.formularioActividad.name)
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

    )


}

@Composable
fun DatosPerfilPro(estado: UiState) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(bottom = 1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(BlancoFondo)
                .padding(4.dp)
        ) {
            Card(shape = CircleShape) {
                Image(
                    painter = painterResource(id = estado.usuario!!.foto),
                    contentDescription = estado.usuario!!.nombre,
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = estado.usuario!!.nombreCompleto(),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadOfertada(
    actividad: Actividad,
    vm: AppViewModel,
    navController: NavHostController,
    estado: UiState,
    setBorrarActividad: (Actividad?) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        val tam = 150.dp
        var menuActividad by remember { mutableStateOf(false) }

        Card(//shape = RectangleShape, /*cuadrado*/
            onClick = {
                vm.selectActividad(actividad)
                vm.ocultarPanelNavegacion()
                navController.navigate(Pantallas.vistaActividadPro.name)
            }) {
            Image(
                painter = painterResource(id = actividad.imagen),
                contentDescription = actividad.titulo,
                modifier = Modifier
                    .width(tam)
                    .height(tam * 2 / 3),
                contentScale = ContentScale.Crop
            )
        }
//        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.widthIn(max = tam * 8 / 10)) {
            Text(
                text = actividad.titulo,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )

            Text(text = actividad.ubicacion ?: "", fontSize = pequena)

        }

        IconButton(onClick = { menuActividad = true }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "abrir submenú",
                tint = AzulAguaClaro
            )

        }

        //Submenu editar
        DropdownMenu(
            expanded = menuActividad,
            onDismissRequest = { menuActividad = false },
            modifier = Modifier.background(BlancoFondo)
        ) {

            DropdownMenuItem(
                text = { Text(text = "Editar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit",
                        tint = AzulAguaClaro
                    )
                },
                onClick = {
                    vm.selectModActividad(actividad)
                    vm.ocultarPanelNavegacion()
                    navController.navigate(Pantallas.modificarActividad.name)
                })
            DropdownMenuItem(
                text = { Text(text = "Eliminar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "borrar",
                        tint = AzulAguaClaro
                    )
                },
                onClick = {
                    setBorrarActividad(actividad)
                })

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PrincipalProPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            BarraSuperiorPro(navController, vm, estado)
        },
        content = { innerPadding ->
            ContenidoPrincipalPro(
                innerPadding,
                navController,
                vm,
                estado,
                { }
            )
        },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacionPro(navController = navController, vm, estado) }
    )
}