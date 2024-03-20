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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.Data.DatosPrueba
import com.dam2.proyectocliente.model.Actividad
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.AzulLogo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBusqueda(navController: NavHostController) {
    Scaffold(
        topBar = { BarraSuperiorBusqueda(navController = navController) },
        content = { innerPadding -> ContenidoBusqueda(innerPadding) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorBusqueda(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        TextField(
            value = "",
            onValueChange = { it },
            singleLine = true,
            label = { Text(text = "Buscar") },
            //leadingIcon = { Icon(painter = painterResource(id = R.drawable.money), contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done  //tipo de botón
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "buscar",
            tint = NegroClaro
        )
    }
}


@Composable
fun CategoriasBusqueda() {
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
fun ContenidoBusqueda(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
        //.verticalScroll(rememberScrollState())
    ) {

        LazyColumn {
            item { CategoriasBusqueda() } //coge el del menú principal
            items(DatosPrueba.actividades) { a ->
                MiniaturaActividadBusqueda(a)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadBusqueda(a: Actividad) {
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
fun MenuBusquedaPreview() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { BarraSuperiorBusqueda(navController = navController) },
        content = { innerPadding -> ContenidoBusqueda(innerPadding) },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacion(navController = navController) }
    )
}