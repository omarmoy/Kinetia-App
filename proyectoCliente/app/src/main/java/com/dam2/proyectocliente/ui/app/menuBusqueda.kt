package com.dam2.proyectocliente.ui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.pequena

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBusqueda(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    Scaffold(
        topBar = { BarraSuperiorBusqueda() },
        content = { innerPadding -> ContenidoBusqueda(innerPadding, navController, vm, estado) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorBusqueda() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        TextField(
            value = "",
            onValueChange = { it },
            singleLine = true,
            label = { Text(text = "Buscar") },
            //leadingIcon = { Icon(painter = painterResource(id = R.drawable.money), contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done  //tipo de botón
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "buscar",
            tint = NegroClaro
        )
    }
}


@Composable
fun ContenidoBusqueda(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    estado: UiState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
        //.verticalScroll(rememberScrollState())
    ) {

        LazyColumn {
            item { Categorias(navController, vm, estado) } //función definida en menuPrincipal
            if (vm.returnActividades().size > 0) {
                items(vm.returnActividades()) { a ->
                    MiniaturaActividadBusqueda(a, vm, navController, estado)
                }
            } else
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                        ) {
                        Spacer(modifier = Modifier.height(150.dp))
                        Text(text = "Ups, no se ha encontrado lo que buscas")
                    }
                }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadBusqueda(
    a: Actividad,
    vm: AppViewModel,
    navController: NavHostController,
    estado: UiState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Define un estado mutable para actuar como un disparador de recomposición
        val recomposeTrigger = remember { mutableStateOf(0) }

        // Función para refrescar manualmente la componible
        fun refreshComposable() {
            recomposeTrigger.value++
        }
        Card(shape = RectangleShape, /*cuadrado*/
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
                    .aspectRatio(3f / 2f),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            colors = CardDefaults.cardColors(AzulAguaFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {

                Column(modifier = Modifier) {
                    Text(
                        text = a.titulo,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,

                        )
                    Text(text = a.ubicacion ?: "", fontSize = pequena)
                }

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
        LaunchedEffect(recomposeTrigger.value) {
            // Esta parte se ejecutará cada vez que cambie el valor de recomposeTrigger
            // Puedes colocar aquí el contenido que quieres refrescar manualmente
            // por ejemplo, otras composables o lógica que desees ejecutar nuevamente
        }

    }


}

/**
 * VISTA PREVIA
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MenuBusquedaPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = { BarraSuperiorBusqueda() },
        content = { innerPadding -> ContenidoBusqueda(innerPadding, navController, vm, estado) },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacion(navController = navController, vm, estado) }
    )
}