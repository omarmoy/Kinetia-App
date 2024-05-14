package com.dam2.proyectocliente.ui.menus.consumer

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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.NavigationPanel
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.small

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMenu(
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState,
    searchFocus: Boolean = false
) {
    var backToUp by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopBarSearch(vm, uiState, focusRequester)
        },
        content = { innerPadding ->
            ContentSearch(innerPadding, navController, vm, uiState, listState)
        },
        floatingActionButton = {
            Surface(
                //shadowElevation = 4.dp, // Establece la elevación a 4dp
                shape = CircleShape,
                color = BlancoFondo.copy(alpha = 0.5f) //establece el color con transparencia
            ) {
            IconButton(onClick = {
                backToUp = true
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Volver Arriva",
                    tint = NegroClaro
                )
            }
        }}
    )
    LaunchedEffect(backToUp) {
        if (backToUp) {
            listState.animateScrollToItem(index = 0)
            backToUp = false
        }
    }
    if (searchFocus) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch(vm: AppViewModel, uiState: UiState, focus: FocusRequester) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        TextField(
            value = uiState.activitySearched,
            onValueChange = { vm.setActividadBuscar(it) },
            singleLine = true,
            label = { Text(text = "Buscar") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default  //tipo de botón
            ),
            trailingIcon = {
                if (uiState.activitySearched != "")
                    IconButton(onClick = { vm.setActividadBuscar("")}) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "buscar",
                            tint = NegroClaro
                        )
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focus)
        )
    }
}


@Composable
fun ContentSearch(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState,
    listState: LazyListState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
    ) {

        LazyColumn(state = listState) {
            item { Categories(navController, vm, uiState) } //función definida en menuPrincipal
            if (vm.listaActividades().size > 0) {
                items(vm.listaActividades()) { a ->
                    ActivitySearch(
                        a,
                        vm,
                        navController,
                        uiState
                    )
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
                        Spacer(modifier = Modifier.height(550.dp))
                    }
                }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitySearch(
    a: Activity,
    vm: AppViewModel,
    navController: NavHostController,
    estado: UiState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val triggerFav = remember { mutableIntStateOf(0) }
        fun refreshComposable() {
            triggerFav.intValue++
        }

        Card(shape = RectangleShape, /*cuadrado*/
            onClick = {
                vm.selectActivity(a)
                vm.ocultarPanelNavegacion()
                navController.navigate(Screens.vistaActividad.name)
            }) {
            Image(
                painter = painterResource(Painter.getActivityPictureInt(a.picture)),
                contentDescription = a.title,
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
                        text = a.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,

                        )
                    Text(text = a.location, fontSize = small)
                }

                Column(
                    modifier = Modifier
                        //.weight(0.1f)
                        .fillMaxHeight()
                        .padding(0.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    IconButton(onClick = {
                        if (estado.isFavorite(a))
                            vm.deleteFav(a)
                        else
                            vm.addFav(a)
                        refreshComposable()
                    }) {
                        Icon(
                            imageVector = if (estado.isFavorite(a)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Fav",
                            tint = AzulAguaOscuro
                        )
                    }
                }
            }
        }
        LaunchedEffect(triggerFav.intValue) {
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
fun SearchPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopBarSearch(
                vm,
                estado,
                FocusRequester()
            )
        },
        content = { innerPadding ->
            ContentSearch(
                innerPadding,
                navController,
                vm,
                estado,
                LazyListState()
            )
        },
        //llama a una función de navegación:
        bottomBar = { NavigationPanel(navController = navController, vm, estado) }
    )
}