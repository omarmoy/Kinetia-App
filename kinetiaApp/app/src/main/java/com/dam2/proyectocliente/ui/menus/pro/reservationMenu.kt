package com.dam2.proyectocliente.ui.menus.pro

import android.os.Handler
import android.os.Looper
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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.NavigationPanelPro
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.menus.DropdownConfig
import com.dam2.proyectocliente.ui.menus.consumer.Title
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.small
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationMenu(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    Scaffold(topBar = { TopBarReservations(navController, vm, uiState) },
        content = { innerPadding ->
            ContentReservations(innerPadding, navController, vm, uiState)
        })


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarReservations(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    var showSettings by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(bottom = 1.dp)
    ) {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
            title = { Title(texto = "Mis Reservas") },
            actions = {
                IconButton(onClick = {
                    if (estado.user!!.tieneMensajesSinLeer()) {
                        vm.filtrarMensajesNoleidos()
                        vm.cambiarBotonNav(2)
                        navController.navigate(Screens.menuMensajes.name)
                    } else {
                        showSnackbar = true
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "notificacion",
                        tint = if (estado.user!!.tieneMensajesSinLeer()) Rojo else AzulAguaOscuro
                    )
                }


                //Ajustes
                IconButton(onClick = { showSettings = !showSettings }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Ajustes",
                        tint = AzulAguaOscuro
                    )
                }

                DropdownConfig(navController, vm, estado, showSettings) {
                    showSettings = false
                }

            })

        if (showSnackbar) {
            Snackbar(containerColor = BlancoFondo, content = {
                Text(
                    "No tiene mensajes nuevos",
                    color = NegroClaro,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            })
        }

        LaunchedEffect(showSnackbar) {
            if (showSnackbar) {
                // Si la Snackbar está visible, esperar un tiempo y luego ocultarla automáticamente
                delay(1000)
                showSnackbar = false
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentReservations(
    innerPadding: PaddingValues, navController: NavHostController, vm: AppViewModel, estado: UiState
) {

    val reservations = estado.user!!.activitiesOffered.filter { activity ->
        activity.reservations.isNotEmpty()
    }

    Scaffold(modifier = Modifier.padding(innerPadding), content = { paddinHijo ->
        Column(modifier = Modifier.padding(paddinHijo)) {
            LazyColumn(modifier = Modifier.padding(8.dp)) {

                if (reservations.isEmpty()) {
                    item {
                        Text(
                            text = "No tienes reservas",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(reservations) { activity ->
                        Reservation(
                            activity, vm, navController
                        )
                    }
                }
            }
        }
    })


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reservation(
    activity: Activity, vm: AppViewModel, navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gris2)
            .padding(1.dp),
        onClick = {
            vm.ocultarPanelNavegacion()
            navController.navigate(Screens.vistaReservasActividad.name)
        }) {
        Row(
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
                    vm.selectActivity(activity)
                    vm.ocultarPanelNavegacion()
                    val mainHandler = Handler(Looper.getMainLooper())
                    mainHandler.postDelayed({
                        navController.navigate(Screens.vistaReservasActividad.name)
                    }, 100)
                }) {
                Image(
                    painter = painterResource(id = Painter.getActivityPictureInt(activity.picture)),
                    contentDescription = activity.title,
                    modifier = Modifier
                        .width(tam)
                        .height(tam * 2 / 3),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(24.dp))

            Column {
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
                    text = "Número de reservas: " + activity.reservations.size, fontSize = small
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
    val uiState by vm.uiState.collectAsState()
    Scaffold(topBar = {
        TopBarReservations(navController, vm, uiState)
    }, content = { innerPadding ->
        ContentReservations(
            innerPadding, navController, vm, uiState
        )
    },
        //llama a una función de navegación:
        bottomBar = { NavigationPanelPro(navController = navController, vm, uiState) })
}