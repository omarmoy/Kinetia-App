package com.dam2.proyectocliente.ui.menus.consumer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.dam2.proyectocliente.NavigationPanel
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.menus.DropdownConfig
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.utils.Painter
import com.dam2.proyectocliente.utils.showDate
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.small
import kotlinx.coroutines.delay
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMenu(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    var deleteAdvertisement by remember { mutableStateOf<Advertisement?>(null) }
    val setDeleteAd: (Advertisement?) -> Unit = { ad -> deleteAdvertisement = ad }

    Scaffold(
        topBar = {
            TopBarUserMenu(
                navController = navController,
                vm,
                uiState
            )
        },
        content = { innerPadding ->
            ContentUserMenu(
                innerPadding,
                navController,
                vm,
                uiState,
                setDeleteAd
            )
        }
    )

    if (deleteAdvertisement != null) {
        DialogInfo(
            onDismissRequest = { setDeleteAd(null) },
            onConfirmation = { vm.deleteAdvertisement(deleteAdvertisement!!); setDeleteAd(null) },
            dialogTitle = deleteAdvertisement!!.title,
            dialogText = "¿Quieres borrar este anunco?",
            buttonConfirm = "Aceptar",
            buttonDismiss = "Cancelar"
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUserMenu(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    var showConfig by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
        title = { /*sin título*/ },
        actions = {
            IconButton(onClick = {
                if (uiState.user!!.tieneMensajesSinLeer()) {
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
                    tint = if (uiState.user!!.tieneMensajesSinLeer()) Rojo else AzulAguaOscuro
                )
            }

            //Ajustes
            IconButton(onClick = { showConfig = !showConfig }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Ajustes",
                    tint = AzulAguaOscuro
                )
            }

            DropdownConfig(navController, vm, uiState, showConfig) { showConfig = false }


        }
    )

    if (showSnackbar) {
        Snackbar(
            containerColor = BlancoFondo,
            content = {
                Text(
                    "No tiene mensajes nuevos",
                    color = NegroClaro,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            delay(1000)
            showSnackbar = false
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentUserMenu(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState,
    setDeleteAd: (Advertisement) -> Unit
) {
    var showProfile by rememberSaveable { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddingSon ->
            Column(
                modifier = Modifier
                    .padding(paddingSon)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                ) {
                    Button(
                        onClick = { showProfile = true },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (showProfile) AzulAguaClaro else AzulAguaOscuro
                        ),
                        contentPadding = PaddingValues(8.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Perfil",
                            color = if (showProfile) NegroClaro else Color.White
                        )
                    }
                    Button(
                        onClick = { showProfile = false },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!showProfile) AzulAguaClaro else AzulAguaOscuro
                        ),
                        contentPadding = PaddingValues(8.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Mis anuncios",
                            color = if (!showProfile) Color.Black else Color.White
                        )
                    }
                }

                LazyColumn {
                    if (showProfile)
                        item {
                            PanelPerfil(
                                navController,
                                vm,
                                uiState
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
                        items(uiState.user!!.advertisements) { anuncio ->
                            MiniaturaAnuncio(
                                anuncio,
                                navController,
                                vm,
                                setDeleteAd
                            )
                        }

                        if (uiState.user.advertisements.size == 0) {
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
            if (!showProfile) {
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
fun PanelPerfil(navController: NavHostController, vm: AppViewModel, uiState: UiState) {
    DatosPerfil(uiState)
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
        items(uiState.user!!.activitiesReserved) { a ->
            ActivityScrollLateral(
                a,
                vm,
                navController,
                uiState,
                true
            )
        }
        if (uiState.user.activitiesReserved.size == 0) {
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
        items(uiState.user!!.activitiesFav) { a ->
            ActivityScrollLateral(
                a,
                vm,
                navController,
                uiState,
                true
            )
        }
        if (uiState.user.activitiesFav.size == 0) {
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
fun DatosPerfil(uiState: UiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height(75.dp)
            .background(AzulAguaClaro)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = AzulAguaClaro)
        ) {
            Image(
                painter = painterResource(
                    Painter.getProfilePictureInt(uiState.user!!.profilePicture)
                ),
                contentDescription = uiState.user.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(75.dp),
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
                text = uiState.user!!.fullName(),
                fontSize = 18.sp
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaAnuncio(
    advertisement: Advertisement,
    navController: NavHostController,
    vm: AppViewModel,
    setDeleteAd: (Advertisement) -> Unit
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
                    Text(
                        text = showDate(
                            advertisement.creationDate
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                        ),
                        fontSize = 12.sp
                    )
                    IconButton(onClick = {
                        setDeleteAd(advertisement)
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
fun ProfilePreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    val setDeleteAd: (Advertisement) -> Unit = { }
    Scaffold(
        topBar = {
            TopBarUserMenu(
                navController,
                vm,
                uiState
            )
        },
        content = { innerPadding ->
            ContentUserMenu(
                innerPadding,
                navController,
                vm,
                uiState,
                setDeleteAd
            )
        },
        //llama a una función de navegación:
        bottomBar = { NavigationPanel(navController = navController, vm, uiState) }
    )
}