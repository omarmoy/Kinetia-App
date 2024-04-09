package com.dam2.proyectocliente.ui.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.model.Actividad
import com.example.proyectocliente.ui.theme.BlancoFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaActividad(navController: NavHostController, actividad: Actividad, vm: AppViewModel) {

    Scaffold(
        topBar = { BarraSuperiorActividad(navController, actividad, vm) },
        content = { innerPadding -> ContenidoActividad(innerPadding, navController, actividad, vm) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorActividad(
    navController: NavHostController, actividad: Actividad, vm: AppViewModel
) {
    TopAppBar(
        title = {
            //Text(actividad.titulo, overflow = TextOverflow.Ellipsis)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                vm.mostrarPanelNavegacion()
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        },
        actions = {
            Row() {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "share")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "Fav")
                }
            }

        }
    )
}

@Composable
fun ContenidoActividad(
    innerPadding: PaddingValues,
    navController: NavHostController,
    actividad: Actividad,
    vm: AppViewModel
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            //.fillMaxSize()
            //.verticalScroll(rememberScrollState())
            .background(color = BlancoFondo)
    ) {

        item {
            Image(
                painter = painterResource(id = actividad.imagen),
                contentDescription = actividad.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f),
                contentScale = ContentScale.Crop
            )
        }
        item { PanelTitulo(navController, actividad, vm) }
        item { PanelDatos(navController, actividad, vm) }
        item { PanelBotones(navController, actividad, vm) }
        item { PanelContenido(navController, actividad, vm) }


        /*Text(text = stringResource(id = actividad.contenido))

        Text(text = actividad.anunciante)
        Text(text = actividad.fecha.toString())
        Text(text = actividad.duracion.toString() ?: "sinDuracion")
        Text(text = actividad.precio.toString() ?: "gratis")
        Text(text = actividad.ubicacion ?: "no hay ubicación")

        Button(onClick = { *//*TODO()*//* }) {
            Text(text = "Contactar")
        }*/

    }
}

@Composable
fun PanelTitulo(navController: NavHostController, actividad: Actividad, vm: AppViewModel) {
    Column(modifier = Modifier.padding(12.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = actividad.titulo)
                Text(text = actividad.ubicacion)
                Text(text = actividad.anunciante)
            }
            IconButton(
                modifier = Modifier.weight(.2f),
                onClick = {
                    /*TODO*/
                }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "compartir")
            }
        }
        Text(
            text = "publicado: " + actividad.fechaPublicacion.mostrarFecha(),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun PanelDatos(navController: NavHostController, actividad: Actividad, vm: AppViewModel) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "fecha")
        //Icon(imageVector = Icons.Filled., contentDescription = "fecha")
    }
}

@Composable
fun PanelBotones(navController: NavHostController, actividad: Actividad, vm: AppViewModel) {

}

@Composable
fun PanelContenido(navController: NavHostController, actividad: Actividad, vm: AppViewModel) {

}

/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun actividadPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val a = DatosPrueba.actividades[0]
    VistaActividad(navController = navController, actividad = a, vm)
}