package com.dam2.proyectocliente.ui.vistas.consumidor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaFondo2
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.pequena

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaAnuncio(
    navController: NavHostController,
    anuncio: Anuncio,
    vm: AppViewModel,
    vistaPrevia: Boolean = false
) {

    Scaffold(
        topBar = {
            if (vistaPrevia)
                BarraSuperiorAnuncioVP()
            else
                BarraSuperiorAnuncio(navController, anuncio, vm)
        },
        content = { innerPadding -> ContenidoAnuncio(innerPadding, anuncio) },
        bottomBar = {
            if (anuncio.anuncianteID != vm.usuario().id)
                BotonContactar(navController, anuncio, vm)
            if (vistaPrevia)
                BarraInferiorAnuncioVP(navController, vm)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorAnuncioVP() {
    TopAppBar(
        title = {
            Text(
                text = "Vista Previa",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "atrás"
            )
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorAnuncio(
    navController: NavHostController, anuncio: Anuncio, vm: AppViewModel
) {
    TopAppBar(
        title = { },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                vm.mostrarPanelNavegacion()
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "atrás"
                )
            }
        },
        actions = {
            if (anuncio.anuncianteID == vm.usuario().id) {
                IconButton(onClick = {
                    vm.selectModAnuncio(anuncio)
                    navController.navigate(Pantallas.modificarAnuncio.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit",
                        tint = AzulAguaOscuro
                    )
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
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = AzulAguaFondo2),
            modifier = Modifier
                .size(150.dp)
//                .padding(top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = anuncio.fotoAnunciante),
                contentDescription = anuncio.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = anuncio.anunciante, color = AzulAguaClaro, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = anuncio.titulo, color = AzulAguaOscuro, fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Text(text = "Ubicacion: " + anuncio.localidad, color = AzulAguaClaro, fontSize = 16.sp)

            Text(
                text = "Publicado " + anuncio.fecha.mostrarFecha(),
                color = AzulAguaClaro,
                fontSize = 16.sp
            )
        }

        Surface(
            modifier = Modifier
                .background(color = AmarilloPastel)
                .padding(top = 1.dp)
        ) {

            Text(
                text = if (anuncio.contenido == "")  //TODO quitar stringResource()
                    stringResource(id = anuncio.contenidoInt)
                else
                    anuncio.contenido,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlancoFondo)
                    .padding(16.dp)
            )


        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonContactar(navController: NavHostController, anuncio: Anuncio, vm: AppViewModel) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .background(BlancoFondo)
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = AzulAguaOscuro),
                    modifier = Modifier.size(30.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "contactar",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Text(text = "Contactar", fontSize = pequena)
            }

        }
    }
}

@Composable
fun BarraInferiorAnuncioVP(
    navController: NavHostController,
    vm: AppViewModel
) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(BlancoFondo)
        ) {
            TextButton(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Editar", color = AzulAguaOscuro, fontSize = 16.sp)
            }

            TextButton(onClick = {
                vm.resetNuevoAnuncio()
                vm.mostrarPanelNavegacion()
                navController.navigate(Pantallas.menuUsuario.name)
            }) {
                Text(text = "Cancelar", color = AzulAguaOscuro, fontSize = 16.sp)
            }
            TextButton(onClick = {
                vm.publicarAnuncio()
                vm.mostrarPanelNavegacion()
                navController.navigate(Pantallas.menuUsuario.name)

            }) {
                Text(text = "Publicar", color = AzulAguaOscuro, fontSize = 16.sp)
            }
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