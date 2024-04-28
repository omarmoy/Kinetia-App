package com.dam2.proyectocliente.ui.vistas.pro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaActividadPro(
    navController: NavHostController,
    actividad: Actividad,
    vm: AppViewModel,
    estado: UiState,
    vistaPrevia: Boolean = false,
) {

    Scaffold(
        topBar = {
            if (vistaPrevia)
                BarraSuperiorActividadVistaPrevia()
            else
                BarraSuperiorActividad(navController, vm, actividad)
        },
        content = { innerPadding ->
            ContenidoActividad(innerPadding, actividad)
        },
        bottomBar = {
            if (vistaPrevia)
                BarraInferiorActividadVP(navController, actividad, vm, estado)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorActividad(
    navController: NavHostController, vm: AppViewModel, actividad: Actividad
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
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Cerrar"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                vm.selectModActividad(actividad)
                navController.navigate(Pantallas.modificarActividad.name)
            }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Fav",
                    tint = AzulAguaOscuro
                )
            }
        }
    )
}


@Composable
fun ContenidoActividad(
    innerPadding: PaddingValues,
    actividad: Actividad
) {
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
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
            contentScale = ContentScale.Crop
        )

        PanelTitulo(actividad)
        PanelDatos(actividad)
        PanelBotones()
        PanelContenido(actividad)
    }
}

@Composable
fun PanelTitulo(actividad: Actividad) {

    Column(modifier = Modifier.padding(top = 12.dp, end = 12.dp, start = 12.dp, bottom = 1.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = actividad.titulo, color = AzulAguaOscuro, fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = actividad.ubicacion, color = AzulAguaClaro, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = actividad.anunciante, color = AzulAguaClaro, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Plazas disponibles: " + actividad.plazasDisponibles,
                    color = AzulAguaClaro, fontSize = 14.sp
                )
            }
            Icon(
                imageVector = Icons.Filled.Share, contentDescription = "compartir",
                tint = AzulAguaOscuro,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = "publicado: " + actividad.fechaPublicacion.mostrarFecha(),
            textAlign = TextAlign.End, color = AzulAguaClaro, fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun PanelDatos(actividad: Actividad) {
    Surface(
        modifier = Modifier
            .background(color = AmarilloPastel)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = BlancoFondo)
                .padding(top = 12.dp)
        ) {
            val tamIcon = 40.dp
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.DateRange, contentDescription = "fecha",
                    tint = AzulAguaClaro,
                    modifier = Modifier.size(tamIcon)
                )
                Text(
                    text = actividad.fecha.toStringFecha(),
                    color = AzulAguaClaro,
                    fontSize = 14.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_clock),
                    contentDescription = "duración",
                    tint = AzulAguaClaro,
                    modifier = Modifier.size(tamIcon)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = actividad.fecha.toStringHora(),
                    color = AzulAguaClaro,
                    fontSize = 14.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_euro),
                    contentDescription = "euro",
                    tint = AzulAguaClaro,
                    modifier = Modifier.size(tamIcon)
                )
                Text(
                    text = actividad.precio.toString() + " euros",
                    color = AzulAguaClaro,
                    fontSize = 14.sp
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanelBotones() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 70.dp, end = 70.dp, bottom = 12.dp)
    ) {
        Button(
            onClick = { },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(AmarilloPastel),
            contentPadding = PaddingValues(8.dp, 0.dp)
        ) {
            Text(text = "Reservar", color = Color.Black)
        }

        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = AzulAguaOscuro),
            modifier = Modifier.size(50.dp),
            onClick = { }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "contactar",
                    tint = Color.White,
                    modifier = Modifier.size(38.dp)
                )
            }
        }

    }
}

@Composable
fun PanelContenido(actividad: Actividad) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (stringResource(actividad.contenidoPrueba) == "vacio") //TODO QUITAR ESTA MIERDA
                    actividad.contenido
                else
                    stringResource(actividad.contenidoPrueba),
                textAlign = TextAlign.Justify,
                fontSize = 14.sp
            )
        }

        if (actividad.contenido.length > 1399) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {  },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(AmarilloPastel),
                contentPadding = PaddingValues(8.dp, 0.dp)
            ) {

                Text(text = "Reservar", color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorActividadVistaPrevia() {
    TopAppBar(
        title = {
            Text("Vista previa")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "fav",
                tint = AzulAguaOscuro
            )
        }
    )
}

@Composable
fun BarraInferiorActividadVP(
    navController: NavHostController,
    actividad: Actividad,
    vm: AppViewModel,
    estado: UiState,
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
                vm.mostrarPanelNavegacion()
                navController.navigate(Pantallas.menuPrincipalPro.name)
            }) {
                Text(text = "Cancelar", color = AzulAguaOscuro, fontSize = 16.sp)
            }
            TextButton(onClick = {
                vm.publicarActividad()
                vm.mostrarPanelNavegacion()
                navController.navigate(Pantallas.menuPrincipalPro.name)

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
fun ActividadPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val a = DatosPrueba.actividades[0]
    val estado by vm.uiState.collectAsState()
    VistaActividadPro(navController = navController, actividad = a, vm, estado)
}