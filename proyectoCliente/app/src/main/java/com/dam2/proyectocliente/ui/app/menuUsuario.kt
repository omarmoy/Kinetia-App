package com.dam2.proyectocliente.ui.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.dam2.proyectocliente.model.Anuncio
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.AzulLogo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuUsuario(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    Scaffold(
        topBar = { BarraSuperiorPerfil(navController = navController) },
        content = { innerPadding -> ContenidoUsuario(innerPadding, vm, estado) }
    )
}


@Composable
fun BarraSuperiorPerfil(navController: NavHostController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Notifications,
            contentDescription = "notificacion",
            tint = NegroClaro
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Ajustes",
            tint = NegroClaro
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContenidoUsuario(innerPadding: PaddingValues, vm: AppViewModel, estado: UiState) {
    var verPerfil by rememberSaveable { mutableStateOf(true) }
    Scaffold(
        modifier = Modifier.padding(innerPadding),
        content = { paddinHijo ->
            Column(
                modifier = Modifier
                    .padding(paddinHijo)
                    .background(BlancoFondo)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                    //.padding(bottom = 0.dp)
                    //.background(AzulFondo)
                ) {
                    Button(
                        onClick = { verPerfil = true },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (verPerfil) AzulFondo else AzulLogo
                        ),
                        contentPadding = PaddingValues(8.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Perfil",
                            color = if (verPerfil) Color.Black else Color.White
                        )
                    }
                    Button(
                        onClick = { verPerfil = false },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!verPerfil) AzulFondo else AzulLogo
                        ),
                        contentPadding = PaddingValues(8.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Anuncios",
                            color = if (!verPerfil) Color.Black else Color.White
                        )
                    }
                }

                LazyColumn {
                    if (verPerfil)
                        item { PanelPerfil(vm, estado) }
                    else {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp)
                                    .height(75.dp)
                                    .background(AzulFondo)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Publicados",
                                    fontSize = 18.sp
                                )
                                IconButton(onClick = { TODO() }) {
                                    Icon(
                                        imageVector = Icons.Filled.AddCircle,
                                        contentDescription = "nuevoAnuncio",
                                        tint = AzulLogo
                                    )
                                }
                            }
                        }
                        //TODO("controlar cuando no haya publicado ningún anuncio")
                        items(estado.anunciosPublicados) { anuncio -> MiniaturaAnuncio(anuncio) }
                    }
                }
            }
        }

    )


}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PanelPerfil(vm: AppViewModel, estado: UiState) {
    DatosPerfil(vm, estado)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Mis reservas", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(30.dp)
                .width(75.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Ver todo", fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    LazyRow {
        items(DatosPrueba.actividades) { a -> MiniaturaActividadPerfil(a = a) }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Favoritos", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .height(30.dp)
                .width(75.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Ver todo", fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    LazyRow {
        items(DatosPrueba.actividades) { a -> MiniaturaActividadPerfil(a = a) }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatosPerfil(vm: AppViewModel, estado: UiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .height(75.dp)
            .background(AzulFondo)
    ) {
        Card(shape = CircleShape) {
            Image(
                painter = painterResource(id = estado.foto),
                contentDescription = estado.nombre,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = estado.nombre + " " + estado.apellido1 + " " + estado.apellido2,
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadPerfil(a: Actividad) {
    val tam = 180.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RectangleShape,
            onClick = {/*TODO*/ }) {
            Image(
                painter = painterResource(id = a.imagen),
                contentDescription = a.titulo,
                modifier = Modifier
                    //.fillMaxHeight()
                    .width(tam),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            colors = CardDefaults.cardColors(AzulFondo),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 8.dp)
                .width(tam)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = a.titulo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MiniaturaAnuncio(anuncio: Anuncio) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
            .height(60.dp)
    ) {
        Card(
            onClick = { /*TODO*/ },
            shape = RectangleShape
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlancoFondo)
                    .padding(8.dp)
            ) {
                Text(text = anuncio.titulo)
                Text(text = anuncio.fecha.mostrarFecha(), fontSize = 12.sp)
            }
        }

    }
}


/**
 * VISTA PREVIA
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PerfilPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Scaffold(
        topBar = { BarraSuperiorPerfil(navController = navController) },
        content = { innerPadding -> ContenidoUsuario(innerPadding, vm, estado) },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacion(navController = navController, estado) }
    )
}