package com.dam2.proyectocliente.ui.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.Data.DatosPrueba
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.AzulLogo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavHostController) {
    Scaffold(
        topBar = { BarraSuperior(navController = navController) },
        content = { innerPadding -> Contenido(innerPadding) },
        bottomBar = { BarraInferior(navController = navController) }
    )
}

@Composable
fun BarraSuperior(navController: NavHostController) {

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


@Composable
fun Contenido(innerPadding: PaddingValues) {
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
            item(span = { GridItemSpan(2) }) {
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
            items(DatosPrueba.actividades) { a ->
                VistaActividad(a)
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaActividad(a: Actividad) {
    val tam = 150.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RectangleShape,
            onClick = {/*TODO*/ }) {
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
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Compartir"
                        )
                    }
                }

            }

        }

    }

}


@Composable
fun BarraInferior(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFF4F4F4))
            .padding(12.dp)
    ) {
        IconButton(onClick = { navController.navigate(Pantallas.menuPrincipal.name)}) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Inicio",
                tint = NegroClaro
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "Actividades",
                tint = NegroClaro
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "Add",
                tint = AzulLogo
            )
        }
        IconButton (onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.MailOutline,
                contentDescription = "buscar",
                tint = NegroClaro
            )
        }
        IconButton (onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Mi Cuenta",
                tint = NegroClaro
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun menuPrincipalPreview() {
    MenuPrincipal(navController = rememberNavController())
    //VistaActividad(a = DatosPrueba.actividades[0])
}