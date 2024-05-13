package com.dam2.proyectocliente.ui.menus.pro


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.PanelNavegacionPro
import com.dam2.proyectocliente.models.Screens
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAdsMenu(
    navController: NavHostController, vm: AppViewModel, uiState: UiState
) {
    var backToUp by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Scaffold(topBar = {
        TopBarSearchAds(vm, uiState)
    }, content = { innerPadding ->
        Advertisements(innerPadding, navController, vm, listState)
    }, floatingActionButton = {
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
        }
    })
    LaunchedEffect(backToUp) {
        if (backToUp) {
            listState.animateScrollToItem(index = 0)
            backToUp = false
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearchAds(vm: AppViewModel, uiState: UiState) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            //.height(150.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        TextField(
            value = uiState.advertisementSearched,
            onValueChange = { vm.setAdvertisementSearched(it) },
            singleLine = true,
            label = { Text(text = "Buscar Anuncio") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Default  //tipo de botón
            ),
            trailingIcon = {
                if (uiState.advertisementSearched != "") IconButton(onClick = { vm.setAdvertisementSearched("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "buscar",
                        tint = NegroClaro
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun Advertisements(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    listState: LazyListState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
    ) {

        LazyColumn(state = listState) {
            if (vm.advertisementsList().size > 0) {
                items(vm.advertisementsList()) { a ->
                    Advertisement(a, vm, navController)
                }
            } else item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(150.dp))
                    Text(text = "Ups, no se ha encontrado lo que buscas")
                    Spacer(modifier = Modifier.height(600.dp))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Advertisement(
    advertisement: Advertisement, vm: AppViewModel, navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 12.dp, end = 8.dp, top = 12.dp)
            .background(AzulAguaFondo),
        onClick = {
            vm.ocultarPanelNavegacion()
            vm.selectAnuncio(advertisement)
            navController.navigate(Screens.vistaAnuncioPro.name)
        }) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(AzulAguaFondo)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = advertisement.title,
//                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(text = advertisement.userName, fontSize = 16.sp)
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
fun MenuBusquedaPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    Scaffold(topBar = {
        TopBarSearchAds(
            vm, uiState
        )
    }, content = { innerPadding ->
        Advertisements(
            innerPadding, navController, vm, LazyListState()
        )
    },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacionPro(navController = navController, vm, uiState) })

//    val a = DatosPrueba.anuncios[0]
//    MiniaturaAnuncioBusqueda(a, vm, navController, uiState)
}