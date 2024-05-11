package com.dam2.proyectocliente.ui.menus.pro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
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
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.PanelNavegacionPro
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.menus.DesplegableConfiguarion
import com.dam2.proyectocliente.ui.menus.consumer.Titulo
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.utils.selectorActivityPicture
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.small

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationMenu(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var borrarActivity by remember { mutableStateOf<Activity?>(null) }
    val setBorrarActividad: (Activity?) -> Unit = { actividad -> borrarActivity = actividad }

    Scaffold(
        topBar = { BarraSuperiorGR(navController, vm, estado) },
        content = { innerPadding ->
            ContenidoGR(innerPadding, navController, vm, estado, setBorrarActividad)
        }
    )

    if (borrarActivity != null) {
        DialogInfo(
            onDismissRequest = { setBorrarActividad(null) },
            onConfirmation = { vm.deleteActivity(borrarActivity!!); setBorrarActividad(null) },
            dialogTitle = borrarActivity!!.title,
            dialogText = "¿Quieres borrar este actividad?",
            buttonConfirm = "Aceptar",
            buttonDismiss = "Cancelar"
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorGR(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    var mostrarMenu by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(bottom = 1.dp)
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
            title = { Titulo(texto = "Mis Reservas") },
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
                //Spacer(modifier = Modifier.width(12.dp))

                //Ajustes
                IconButton(onClick = { mostrarMenu = !mostrarMenu }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Ajustes",
                        tint = AzulAguaOscuro
                    )
                }

                DesplegableConfiguarion(navController, vm, estado, mostrarMenu) {
                    mostrarMenu = false
                }

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoGR(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState,
    setBorrarActividad: (Activity?) -> Unit
) {

    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddinHijo ->
            Column(modifier = Modifier.padding(paddinHijo)) {
                LazyColumn(modifier = Modifier.padding(8.dp)) {

                    if (estado.user!!.activitiesOffered.size == 0) {
                        item {
                            Text(
                                text = "Todavía no tienes reservas",
                                modifier = Modifier.padding(top = 40.dp)
                            )
                        }
                    } else {
                        items(estado.user!!.activitiesOffered) { actividad ->
                            MiniaturaReserva(
                                actividad, vm, navController
                            )
                        }
                    }
                }
            }
        }
    )


}

@Composable
fun TituloGR() {
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
            Text(
                text = "Gestión de reservas",
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
fun MiniaturaReserva(
    activity: Activity,
    vm: AppViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gris2)
            .padding(1.dp),
        onClick = {
            vm.ocultarPanelNavegacion()
            navController.navigate(Screens.vistaReservasActividad.name)
        }
    ) {
        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
        ) {

            val tam = 100.dp
            Card(
//                shape = RectangleShape,
                onClick = {
                    vm.selectActividad(activity)
                    vm.ocultarPanelNavegacion()
                    navController.navigate(Screens.vistaReservasActividad.name)
                }) {
                Image(
                    painter = painterResource(id = selectorActivityPicture(activity.picture)),
                    contentDescription = activity.title,
                    modifier = Modifier
                        .width(tam)
                        .height(tam * 2 / 3),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(24.dp))

            Column(
//                modifier = Modifier.widthIn(max = tam * 8 / 10)
            ) {
                Text(
                    text = activity.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Plazas disponibles: " + (activity.vacancies - activity.reservations.size),
                    fontSize = small
                )
                Text(
                    text = "Número de reservas: " + activity.reservations.size,
                    fontSize = small
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GRProPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            BarraSuperiorGR(navController, vm, estado)
        },
        content = { innerPadding ->
            ContenidoGR(
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