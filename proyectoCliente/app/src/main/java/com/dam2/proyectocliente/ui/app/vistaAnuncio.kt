package com.dam2.proyectocliente.ui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.model.Anuncio
import com.example.proyectocliente.ui.theme.BlancoFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaAnuncio(navController: NavHostController, anuncio: Anuncio, vm: AppViewModel) {

    Scaffold(
        topBar = { BarraSuperiorAnuncio(navController, anuncio, vm) },
        content = { innerPadding -> ContenidoAnuncio(innerPadding, anuncio) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorAnuncio(
    navController: NavHostController, anuncio: Anuncio, vm: AppViewModel
) {
    TopAppBar(
        title = { Text(anuncio.titulo, overflow = TextOverflow.Ellipsis) },
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
            Row(){
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
fun ContenidoAnuncio(innerPadding: PaddingValues, anuncio: Anuncio) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = BlancoFondo)
    ) {
        Card(shape = CircleShape) {
            Image(
                painter = painterResource(id = anuncio.fotoAnunciante),
                contentDescription = anuncio.titulo,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }

        Text(text = anuncio.fecha.toStringFecha())
        Text(text = anuncio.anunciante)
        Text(text = stringResource(id = anuncio.contendio))

        Button(onClick = { /*TODO()*/ }) {
            Text(text = "Contactar")
        }

    }
}


/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun VistaAnuncioPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val a = DatosPrueba.anuncios[0]
    VistaAnuncio(navController, a, vm)
}