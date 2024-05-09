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
import com.dam2.proyectocliente.utils.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.utils.selectorActivityPicture
import com.dam2.proyectocliente.utils.selectorProfilePicture
import com.dam2.proyectocliente.PanelNavegacionPro
import com.dam2.proyectocliente.models.Pantallas
import com.dam2.proyectocliente.ui.menus.DesplegableConfiguarion
import com.dam2.proyectocliente.ui.menus.consumer.Titulo
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
fun MainMenuPro(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    var deleteActivity by remember { mutableStateOf<Activity?>(null) }
    val setDeleteActivity: (Activity?) -> Unit = { activity -> deleteActivity = activity }

    Scaffold(
        topBar = { TopBarMenuPro(navController, vm, uiState) },
        content = { innerPadding ->
            MainMenuContent(innerPadding, navController, vm, uiState, setDeleteActivity)
        }
    )

    if (deleteActivity != null) {
        DialogoInfo(
            onDismissRequest = { setDeleteActivity(null) },
            onConfirmation = { vm.borrarActividad(deleteActivity!!); setDeleteActivity(null) },
            dialogTitle = deleteActivity!!.title,
            dialogText = "¿Quieres borrar este actividad?",
            buttonConfirm = "Aceptar",
            buttonDismiss = "Cancelar"
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMenuPro(navController: NavHostController, vm: AppViewModel, uiState: UiState) {
    var showSetting by remember { mutableStateOf(false) }
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
                if (uiState.user!!.tieneMensajesSinLeer()) {
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
                    tint = if (uiState.user!!.tieneMensajesSinLeer()) Rojo else AzulAguaOscuro
                )
            }
            //Spacer(modifier = Modifier.width(12.dp))

            //Ajustes
            IconButton(onClick = { showSetting = !showSetting }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Ajustes",
                    tint = AzulAguaOscuro
                )
            }

            DesplegableConfiguarion(navController, vm, uiState, showSetting) { showSetting = false }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuContent(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState,
    setDeleteActivity: (Activity?) -> Unit
) {

    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddinHijo ->
            Column(modifier = Modifier.padding(paddinHijo)) {
                DatosPerfilPro(uiState)
                LazyColumn(modifier = Modifier.padding(8.dp)) {

                    item { Titulo(texto = "Mis Actividades") }

                    if (uiState.user!!.activitiesOffered.size == 0) {
                        item {
                            Text(
                                text = "Todavía no has publicado ninguna actividad",
                                modifier = Modifier.padding(top = 40.dp)
                            )
                        }
                    } else {
                        items(uiState.user!!.activitiesOffered) { actividad ->
//                    items(Moker.activities) { actividad -> //TODO quitar
                            ActivityOffered(
                                actividad, vm, navController, uiState, setDeleteActivity
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
fun DatosPerfilPro(uiState: UiState) {
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
                    painter = painterResource(id = selectorProfilePicture(uiState.user!!.profilePicture)),
                    contentDescription = uiState.user!!.name,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(75.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = uiState.user!!.fullName(),
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
fun ActivityOffered(
    activity: Activity,
    vm: AppViewModel,
    navController: NavHostController,
    uiState: UiState,
    setDeleteActivity: (Activity?) -> Unit
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
                vm.selectActividad(activity)
                vm.ocultarPanelNavegacion()
                navController.navigate(Pantallas.vistaActividadPro.name)
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
//        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.widthIn(max = tam * 8 / 10)) {
            Text(
                text = activity.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )

            Text(text = activity.location ?: "", fontSize = pequena)

        }

        Box {
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
                        vm.selectModActividad(activity)
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
                        setDeleteActivity(activity)
                        menuActividad = false
                    })

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PrincipalProPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopBarMenuPro(navController, vm, uiState)
        },
        content = { innerPadding ->
            MainMenuContent(
                innerPadding,
                navController,
                vm,
                uiState,
                { }
            )
        },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacionPro(navController = navController, vm, uiState) }
    )
}