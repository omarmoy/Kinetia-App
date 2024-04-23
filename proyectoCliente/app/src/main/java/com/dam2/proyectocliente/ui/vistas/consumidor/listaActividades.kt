package com.dam2.proyectocliente.ui.vistas.consumidor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.pequena

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaActividades(
    titulo: String,
    lista: ArrayList<Actividad>,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState
) {
    var volverArriba by remember { mutableStateOf(false) }
    val estadoLista = rememberLazyListState()

    Scaffold(
        topBar = { BarraSuperiorListaA(titulo, navController, vm) },
        content = { innerPadding ->
            ContenidoListaActividades(
                innerPadding,
                navController,
                vm,
                estado,
                lista,
                estadoLista
            )
        },
        floatingActionButton = {
            Surface(
                shape = CircleShape,
                color = BlancoFondo.copy(alpha = 0.5f) //establece el color con transparencia
            ) {
                IconButton(onClick = {
                    volverArriba = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Volver Arriba",
                        tint = NegroClaro
                    )
                }
            }
        }
    )
    LaunchedEffect(volverArriba) {
        if (volverArriba) {
            estadoLista.animateScrollToItem(index = 0)
            volverArriba = false
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorListaA(titulo: String, navController: NavHostController, vm: AppViewModel) {
    TopAppBar(
        title = { Text(text = titulo) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        modifier = Modifier.background(BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                vm.setActividadUsuarioBuscar("")
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Cerrar"
                )
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoListaActividades(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState,
    lista: ArrayList<Actividad>,
    estadoLista: LazyListState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {

        LazyColumn(state = estadoLista) {
            //Bucador TODO(agregar funcionalidad buscador)
            item {
                TextField(
                    value = estado.actividadUsuarioBuscar,
                    onValueChange = { vm.setActividadUsuarioBuscar(it) },
                    singleLine = true,
                    label = { Text(text = "Buscar") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "buscar",
                            tint = NegroClaro
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done  //tipo de botón
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = BlancoFondo,
                        unfocusedIndicatorColor = Gris2
                    ),
                    trailingIcon = {
                        if (estado.actividadUsuarioBuscar != "")
                            IconButton(onClick = { vm.setActividadUsuarioBuscar("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "buscar",
                                    tint = NegroClaro
                                )
                            }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(vm.cargarActividadesUsuario(lista)) { a ->
                MiniaturaActividadUnaLinea(a, vm, navController, estado)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadUnaLinea(
    a: Actividad,
    vm: AppViewModel,
    navController: NavHostController,
    estado: UiState
) {
    Row (modifier = Modifier.fillMaxWidth().padding(12.dp)){
        val tam = 150.dp
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
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.widthIn(max = tam * 8 / 10)) {
            Text(
                text = a.titulo,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )

            Text(text = a.ubicacion, fontSize = pequena)

        }

    }
}


@Preview(showBackground = true)
@Composable
fun ListaAPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    ListaActividades(
        titulo = "Mis reservas",
        lista = vm.listaActividades(),
        navController = navController,
        vm = vm,
        estado = estado
    )
}