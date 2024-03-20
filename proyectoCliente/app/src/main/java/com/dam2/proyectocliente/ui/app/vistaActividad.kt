package com.dam2.proyectocliente.ui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.Data.DatosPrueba
import com.dam2.proyectocliente.model.Actividad
import com.example.proyectocliente.ui.theme.BlancoFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaActividad(navController: NavHostController, actividad: Actividad) {
    Scaffold(
        topBar = { BarraSuperiorActividad(navController = navController, actividad = actividad) },
        content = { innerPadding -> ContenidoActividad(innerPadding, actividad) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorActividad(navController: NavHostController, actividad: Actividad) {
    TopAppBar(
        title = { Text(actividad.titulo) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        }
    )
}

@Composable
fun ContenidoActividad(innerPadding: PaddingValues, actividad: Actividad) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = BlancoFondo)
    ) {
        Image(
            painter = painterResource(id = actividad.imagen),
            contentDescription = actividad.titulo,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(text = stringResource(id = actividad.contendio))

        Text(text = actividad.anunciante)
        Text(text = actividad.fecha.toString())
        Text(text = actividad.duracion.toString() ?: "sinDuracion")
        Text(text = actividad.precio.toString() ?: "gratis")
        Text(text = actividad.ubicacion ?: "no hay ubicación")

        Button(onClick = { TODO() }) {
            Text(text = "Contactar")
        }

    }
}


/**
 * VISTA PREVIA
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun actividadPreview() {
    val navController = rememberNavController()
    val a = DatosPrueba.actividades[0]
    VistaActividad(navController = navController, actividad = a)
}