package com.dam2.proyectocliente.ui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.AzulLogo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    Scaffold(
        topBar = { BarraSuperiorMPrincipal(navController = navController) },
        content = { innerPadding -> ContenidoInicio(innerPadding, vm, estado, navController) }
    )
}

/**
 * MENÚ SUPERIOR
 */
@Composable
fun BarraSuperiorMPrincipal(navController: NavHostController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_grain),
                contentDescription = "",
                tint = AzulLogo
            )
            Text(text = "Kinétia", fontWeight = FontWeight.Bold, fontSize = 24.sp)

        }
        Row {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "notificacion",
                tint = NegroClaro
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "buscar",
                tint = NegroClaro
            )
        }
    }

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
            item(span = { GridItemSpan(2) }) { Categorias()}
            item(span = { GridItemSpan(2) }) { DestacadosyRecientes(vm, estado, navController)}

            //Descubre:
            items(DatosPrueba.actividades) { a ->
                MiniaturaActividad(a, vm, navController)
            }
        }


    }
}

@Composable
fun Categorias(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 12.dp)
    ) {

        LazyRow {
            items(DatosPrueba.categorias) { c ->

                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulLogo),
                    contentPadding = PaddingValues(8.dp, 0.dp)
                ) {
                    Text(text = c)
                }
            }
        }
    }
}

@Composable
fun DestacadosyRecientes(vm: AppViewModel, estado: UiState, navController: NavHostController) {
    Column {

        Text(text = "Destacado")
        LazyRow{
            items(estado.actividades){a-> MiniaturaActividad(a, vm, navController)}
        }
        Text(text = "Recientes")
        LazyRow{
            items(estado.actividades){a-> MiniaturaActividad(a, vm, navController)}
        }

        Text(text = "Descubre")
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividad(a: Actividad, vm: AppViewModel, navController: NavHostController) {
    val tam = 150.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            colors = CardDefaults.cardColors(AzulFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
                .height(tam / 2)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {

                Text(
                    text = a.titulo,
                    maxLines = 2,
                    modifier = Modifier.weight(1f)
                )

                Box(modifier = Modifier.weight(0.2f)) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Compartir"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Compartir"
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
fun MenuInicioPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = { BarraSuperiorMPrincipal(navController = navController) },
        content = { innerPadding -> ContenidoInicio(innerPadding, vm, estado, navController) },
        //llama a una función de AppPrincipal:
        bottomBar = { PanelNavegacion(navController = navController, estado) }
    )
}