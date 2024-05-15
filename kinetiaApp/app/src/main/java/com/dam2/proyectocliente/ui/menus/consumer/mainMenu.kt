package com.dam2.proyectocliente.ui.menus.consumer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableIntStateOf
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
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.NavigationPanel
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAgua
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.small
import com.example.proyectocliente.ui.theme.subtitle
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(navController: NavHostController, vm: AppViewModel, uiState: UiState) {
    Scaffold(
        topBar = {
            TopBarMain(navController, vm, uiState)
        },
        content = { innerPadding ->
            ContentMain(innerPadding, vm, uiState, navController)
        }
    )
}

/**
 * MENÚ SUPERIOR
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    var showSnackbar by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlancoFondo),
        title = {
            Image(
                painter = painterResource(R.drawable.logolinealetraylogo),
                contentDescription = "logo",
                modifier = Modifier.height(35.dp)
                //contentScale = ContentScale.Crop
            )
        },
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
            //Spacer(modifier = Modifier.width(12.dp))
            IconButton(onClick = {
                vm.cambiarBotonNav(1)
                vm.setIndiceCategoria()
                vm.selectCategoria(Category.TODO)
                navController.navigate(Screens.menuBusquedaDirecta.name)
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "busquedaDirecta",
                    tint = AzulAguaOscuro
                )
            }

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

/**
 * CONTENEDOR PRINCIPAL
 */
@Composable
fun ContentMain(
    innerPadding: PaddingValues,
    vm: AppViewModel,
    uiState: UiState,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Categories(
                    navController,
                    vm,
                    uiState
                )
            }
            item(span = { GridItemSpan(2) }) {
                FeaturedAndRecent(
                    vm,
                    uiState,
                    navController
                )
            }
        }


    }
}

@Composable
fun Categories(
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState
) {
    val uiStateLista = rememberLazyListState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 12.dp)
    ) {

        LazyRow(state = uiStateLista) {
            items(Category.values()) { categoria ->
                if (!(categoria == Category.TODO && uiState.buttonsNav[0])) {
                    val colorBoton: Color
                    val colorTexto: Color
                    if (categoria == uiState.selectedCategory && !uiState.buttonsNav[0]) {
                        colorBoton = AmarilloPastel
                        colorTexto = NegroClaro
                    } else {
                        colorBoton = AzulAgua
                        colorTexto = Color.White
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            vm.selectCategoria(categoria)
                            if (uiState.buttonsNav[0]) {
                                vm.cambiarBotonNav(1)
                                vm.setIndiceCategoria(categoria)
                                navController.navigate(Screens.menuBuscar.name)
                            }
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(colorBoton),
                        contentPadding = PaddingValues(8.dp, 0.dp)
                    ) {
                        Text(text = categoria.toString(), color = colorTexto)
                    }
                }
            }
        }
    }
    LaunchedEffect(uiState.indiceCategoria != 0) {
        uiStateLista.animateScrollToItem(index = uiState.indiceCategoria)
    }
}

@Composable
fun FeaturedAndRecent(vm: AppViewModel, uiState: UiState, navController: NavHostController) {
    Column(modifier = Modifier.padding(start = 12.dp)) {

        Title(texto = "Destacado")
        LazyRow {
            items(vm.actividadesDestacadas()) { a ->
                ActivityScrollLateral(
                    a,
                    vm,
                    navController,
                    uiState
                )
            }
        }
        Title(texto = "Recientes")
        LazyRow {
            items(vm.actividadesRecientes()) { a ->
                ActivityScrollLateral(
                    a,
                    vm,
                    navController,
                    uiState
                )
            }
        }
    }
}

@Composable
fun Title(texto: String) {
    Text(
        text = texto, fontSize = subtitle,
        fontWeight = FontWeight.Bold, color = AzulAguaOscuro,
        modifier = Modifier.padding(8.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScrollLateral(
    a: Activity,
    vm: AppViewModel,
    navController: NavHostController,
    uiState: UiState,
    showLess: Boolean = false
) {
    val tam = 230.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 12.dp)
    ) {

        val trigger = remember { mutableIntStateOf(0) }
        fun refreshComposable() {
            trigger.intValue++
        }

        Card(
            onClick = {
                vm.selectActivity(a)
                vm.ocultarPanelNavegacion()
                navController.navigate(Screens.vistaActividad.name)
            }) {
            Image(
                painter = painterResource(id = Painter.getActivityPictureInt(a.picture)),
                contentDescription = a.title,
                modifier = Modifier
                    .width(tam)
                    .height(tam * 2 / 3),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            colors = CardDefaults.cardColors(AzulAguaFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
                .width(tam)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {

                Column(modifier = Modifier.widthIn(max = tam * 8 / 10)) {
                    Text(
                        text = a.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,

                        )
                    if (!showLess) {
                        Text(text = a.location, fontSize = small)
                    }
                }

                if (!showLess) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(0.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        IconButton(onClick = {
                            if (uiState.isFavorite(a))
                                vm.deleteFav(a)
                            else
                                vm.addFav(a)
                            refreshComposable()
                        }) {
                            Icon(
                                imageVector = if (uiState.isFavorite(a)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Fav",
                                tint = AzulAguaOscuro
                            )
                        }
                    }
                }
            }
        }
        LaunchedEffect(trigger.intValue) {
            //lanza un efecto vacío que fuerza el refresco de pantalla
        }

    }


}


/**
 * VISTA PREVIA
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainMenuProPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.background(BlancoFondo),
        topBar = {
            TopBarMain(
                navController,
                vm,
                uiState
            )
        },
        content = { innerPadding ->
            ContentMain(
                innerPadding,
                vm,
                uiState,
                navController
            )
        },
        //llama a una función de AppPrincipal:
        bottomBar = { NavigationPanel(navController = navController, vm, uiState) }
    )
}
