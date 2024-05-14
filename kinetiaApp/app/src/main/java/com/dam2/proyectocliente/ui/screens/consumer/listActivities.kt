package com.dam2.proyectocliente.ui.screens.consumer

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
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.small

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListActivities(
    title: String,
    listActivities: ArrayList<Activity>,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState
) {
    var backToUp by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Scaffold(
        topBar = { TopBarList(title, navController, vm) },
        content = { innerPadding ->
            List(
                innerPadding,
                navController,
                vm,
                uiState,
                listActivities,
                listState
            )
        },
        floatingActionButton = {
            Surface(
                shape = CircleShape,
                color = BlancoFondo.copy(alpha = 0.5f) //establece el color con transparencia
            ) {
                IconButton(onClick = {
                    backToUp = true
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
    LaunchedEffect(backToUp) {
        if (backToUp) {
            listState.animateScrollToItem(index = 0)
            backToUp = false
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarList(titulo: String, navController: NavHostController, vm: AppViewModel) {
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
fun List(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState,
    list: ArrayList<Activity>,
    listState: LazyListState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {

        LazyColumn(state = listState) {

            item {
                TextField(
                    value = uiState.activityUserSearched,
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
                        if (uiState.activityUserSearched != "")
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

            items(vm.cargarActividadesUsuario(list)) { a ->
                ActivityOneLine(a, vm, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityOneLine(
    a: Activity,
    vm: AppViewModel,
    navController: NavHostController
) {
    Row (modifier = Modifier.fillMaxWidth().padding(12.dp)){
        val tam = 150.dp
        Card(//shape = RectangleShape, /*cuadrado*/
            onClick = {
                vm.selectActivity(a)
                vm.ocultarPanelNavegacion()
                navController.navigate(Screens.vistaActividad.name)
            }) {
            Image(
                painter = painterResource(Painter.getActivityPictureInt(a.picture)),
                contentDescription = a.title,
                modifier = Modifier
                    .width(tam)
                    .height(tam * 2 / 3),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.widthIn(max = tam * 8 / 10)) {
            Text(
                text = a.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )

            Text(text = a.location, fontSize = small)

        }

    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    ListActivities(
        title = "Mis reservas",
        listActivities = vm.listaActividades(),
        navController = navController,
        vm = vm,
        uiState = uiState
    )
}