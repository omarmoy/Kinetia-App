package com.dam2.proyectocliente.ui.vistas.pro


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.DatosPrueba
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.ui.Pantallas
import com.dam2.proyectocliente.ui.menus.MiniaturaContacto
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaReservas(
    navController: NavHostController,
    vm: AppViewModel,
    actividad: Actividad
) {

    Scaffold(
        topBar = { BarraSuperiorReservas(navController, vm, actividad) },
        content = { innerPadding -> ContenidoReservas(innerPadding, vm, navController, actividad) },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorReservas(
    navController: NavHostController, vm: AppViewModel, actividad: Actividad
) {
    Box(
        modifier = Modifier
            .background(color = AmarilloPastel)
            .padding(bottom = 1.dp)
    ) {
        TopAppBar(
            title = { Text(text = actividad.titulo) },
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
            }
        )
    }
}

@Composable
fun ContenidoReservas(
    innerPadding: PaddingValues,
    vm: AppViewModel,
    navController: NavHostController,
    actividad: Actividad
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {

        LazyColumn {
            items(actividad.reservas) { c ->
                MiniaturaReserva(c, navController, vm)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaReserva(c: Contacto, navController: NavHostController, vm: AppViewModel) {
    Card(
        colors = CardDefaults.cardColors(BlancoFondo),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(75.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(shape = CircleShape) {
                Image(
                    painter = painterResource(id = c.foto),
                    contentDescription = c.nombre,
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
                Text(text = c.nombre, fontSize = 20.sp)
                IconButton(
                    onClick = {
                        vm.selectContacto(c)
                        vm.ocultarPanelNavegacion()
                        navController.navigate(Pantallas.chat.name)
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "buscar",
                        tint = AmarilloPastel,
                        modifier = Modifier.size(30.dp)
                    )
                }

            }
        }
    }

}

/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun VistaReservaPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val ac = DatosPrueba.usuario.actividadesOfertadas[0]
    VistaReservas(navController, vm, ac)
}