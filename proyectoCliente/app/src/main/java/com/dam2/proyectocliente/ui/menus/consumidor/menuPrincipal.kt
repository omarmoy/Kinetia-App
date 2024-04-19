package com.dam2.proyectocliente.ui.menus.consumidor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Categoria
import com.dam2.proyectocliente.ui.PanelNavegacion
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAgua
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo
import com.example.proyectocliente.ui.theme.pequena
import com.example.proyectocliente.ui.theme.subtitulo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    Scaffold(
        topBar = {
            BarraSuperiorMPrincipal(navController, vm, estado)
        },
        content = { innerPadding ->
            ContenidoInicio(innerPadding, vm, estado, navController)
        }
    )
}

/**
 * MENÚ SUPERIOR
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorMPrincipal(navController: NavHostController, vm: AppViewModel, estado: UiState) {
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
            IconButton(onClick = {
                vm.cambiarBotonNav(1)
                vm.setIndiceCategoria()
                vm.selectCategoria(Categoria.Todo)
                navController.navigate(Pantallas.menuBusquedaDirecta.name)
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "busquedaDirecta",
                    tint = AzulAguaOscuro
                )
            }

        }
    )
}

/**
 * CONTENEDOR PRINCIPAL
 */
@Composable
fun ContenidoInicio(
    innerPadding: PaddingValues, vm: AppViewModel, estado: UiState, navController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
        //.verticalScroll(rememberScrollState())
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Categorias(
                    navController,
                    vm,
                    estado
                )
            }
            item(span = { GridItemSpan(2) }) {
                DestacadosyRecientes(
                    vm,
                    estado,
                    navController
                )
            }

            //Descubre:
            items(DatosPrueba.actividades) { a ->
                MiniaturaActividad(a, vm, navController)
            }
        }


    }
}

@Composable
fun Categorias(
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState
) {
    val estadoLista = rememberLazyListState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 12.dp)
    ) {

        LazyRow(state = estadoLista) {
            items(estado.categorias) { categoria ->
                if (!(categoria == Categoria.Todo && estado.botoneraNav[0])) {
                    val colorBoton: Color
                    val colorTexto: Color
                    if (categoria == estado.categoriaSelecciononada && !estado.botoneraNav[0]) {
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
                            if (estado.botoneraNav[0]) {
                                vm.cambiarBotonNav(1)
                                vm.setIndiceCategoria(categoria)
                                navController.navigate(Pantallas.menuBuscar.name)
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
    LaunchedEffect(estado.indiceCategoria != 0) {
        estadoLista.animateScrollToItem(index = estado.indiceCategoria)
    }
}

@Composable
fun DestacadosyRecientes(vm: AppViewModel, estado: UiState, navController: NavHostController) {
    Column(modifier = Modifier.padding(start = 12.dp)) {

        Titulo(texto = "Destacado")
        LazyRow {
            items(vm.actividadesDestacadas()) { a ->
                MiniaturaScrollLateral(
                    a,
                    vm,
                    navController,
                    estado
                )
            }
        }
        Titulo(texto = "Recientes")
        LazyRow {
            items(vm.actividadesRecientes()) { a ->
                MiniaturaScrollLateral(
                    a,
                    vm,
                    navController,
                    estado
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Titulo(texto = "Descubre...")
    }
}

@Composable
fun Titulo(texto: String) {
    Text(
        text = texto, fontSize = subtitulo,
        fontWeight = FontWeight.Bold, color = AzulAguaOscuro,
        modifier = Modifier.padding(8.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaScrollLateral(
    a: Actividad,
    vm: AppViewModel,
    navController: NavHostController,
    estado: UiState,
    mostrarMenos: Boolean = false
) {
    val tam = 230.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        // Define un estado mutable para actuar como un disparador de recomposición
        val recomposeTrigger = remember { mutableIntStateOf(0) }

        // Función para refrescar manualmente la componible
        fun refreshComposable() {
            recomposeTrigger.intValue++
        }
        Card(//shape = RectangleShape, /*cuadrado*/
            onClick = {
                vm.selectActividad(a)
                vm.ocultarPanelNavegacion()
                navController.navigate(Pantallas.vistaActividad.name)
            }) {
            Image(
                painter = painterResource(id = a.imagen),
                contentDescription = a.titulo,
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
                        text = a.titulo,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,

                        )
                    if (!mostrarMenos) {
                        Text(text = a.ubicacion, fontSize = pequena)
                    }
                }

                if (!mostrarMenos) {
                    Column(
                        modifier = Modifier
                            //.weight(0.1f)
                            .fillMaxHeight()
                            .padding(0.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        IconButton(onClick = {
                            if (estado.esFavorita(a))
                                vm.eliminarFavorito(a)
                            else
                                vm.addFavorito(a)
                            refreshComposable()
                        }) {
                            Icon(
                                imageVector = if (estado.esFavorita(a)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Fav",
                                tint = AzulAguaOscuro
                            )
                        }
                    }
                }
            }
        }
        LaunchedEffect(recomposeTrigger.intValue) {
            // Esta parte se ejecutará cada vez que cambie el valor de recomposeTrigger
            // Puedes colocar aquí el contenido que quieres refrescar manualmente
            // por ejemplo, otras composables o lógica que desees ejecutar nuevamente
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividad(a: Actividad, vm: AppViewModel, navController: NavHostController) {
    val tam = 150.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
        //modifier = Modifier.padding(start = 12.dp)
    ) {
        Card(shape = RectangleShape,
            onClick = {
                vm.selectActividad(a)
                vm.ocultarPanelNavegacion()
                navController.navigate(Pantallas.vistaActividad.name)
            }) {
            Image(
                painter = painterResource(id = a.imagen),
                contentDescription = a.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(tam),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            colors = CardDefaults.cardColors(AzulAguaFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)

        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = a.titulo,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = a.ubicacion,
                    fontSize = pequena,
                    overflow = TextOverflow.Ellipsis,
                )

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
fun MenuInicioPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            BarraSuperiorMPrincipal(
                navController,
                vm,
                estado
            )
        },
        content = { innerPadding ->
            ContenidoInicio(
                innerPadding,
                vm,
                estado,
                navController
            )
        },
        //llama a una función de AppPrincipal:
        bottomBar = { PanelNavegacion(navController = navController, vm, estado) }
    )
}
