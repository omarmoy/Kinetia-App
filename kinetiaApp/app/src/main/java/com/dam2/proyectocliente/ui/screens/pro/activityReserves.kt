package com.dam2.proyectocliente.ui.screens.pro


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Reservation
import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.BlancoFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityReserves(
    navController: NavHostController,
    vm: AppViewModel,
    activity: Activity
) {
    var deleteReserve by remember { mutableStateOf<Reservation?>(null) }
    val setDeleteReserve: (Reservation?) -> Unit = { book -> deleteReserve = book }

    Scaffold(
        topBar = { TopBarAR(navController, vm, activity) },
        content = { innerPadding ->
            ContentAR(
                innerPadding, vm, navController, activity, setDeleteReserve
            )
        },
    )

    val trigger = remember { mutableIntStateOf(0) }

    fun refreshComposable() {
        trigger.intValue++
    }

    if (deleteReserve != null) {
        DialogInfo(
            onDismissRequest = { setDeleteReserve(null) },
            onConfirmation = {
                vm.cancelReservation(activity, deleteReserve!!)
                refreshComposable()
                setDeleteReserve(null)
            },
            dialogText = "Vas a cancelar la reserva de ${deleteReserve!!.contactName}",
            buttonConfirm = "Continuar",
            buttonDismiss = "Volver"
        )
    }

//    LaunchedEffect(trigger.intValue) {
//
//    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarAR(
    navController: NavHostController, vm: AppViewModel, activity: Activity
) {
    Box(
        modifier = Modifier
            .background(color = AmarilloPastel)
            .padding(bottom = 1.dp)
    ) {
        TopAppBar(
            title = { Text(text = "Reservas " + activity.title) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
            navigationIcon = {
                IconButton(onClick = {
                    vm.mostrarPanelNavegacion()
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "atrás"
                    )
                }
            }
        )
    }
}

@Composable
fun ContentAR(
    innerPadding: PaddingValues,
    vm: AppViewModel,
    navController: NavHostController,
    activity: Activity,
    setDeleteReserve: (Reservation?) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {

        LazyColumn {
            if (activity.reservations.isEmpty()) {
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
                items(activity.reservations) { c ->
                    MiniatureReservation(c, navController, vm, setDeleteReserve)
                }
            }
        }
    }
}


@Composable
fun MiniatureReservation(
    reservation: Reservation,
    navController: NavHostController,
    vm: AppViewModel,
    setDeleteReserve: (Reservation?) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(BlancoFondo),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(75.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp)
        ) {


            Text(text = reservation.contactName, fontSize = 20.sp)

            Row {
                IconButton(
                    onClick = {
                        setDeleteReserve(reservation)
                    }) {

                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "borrar",
                        tint = AzulAguaClaro
                    )

                }

                IconButton(
                    onClick = {
                        vm.createChatIfNoExist(reservation)
                        vm.ocultarPanelNavegacion()
                        navController.navigate(Screens.chat.name)
                    }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "contactar",
                        tint = AmarilloPastel,
                    )
                }
            }
        }
    }

}

/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun ARPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val ac = Moker.activity
    ActivityReserves(navController, vm, ac)
}