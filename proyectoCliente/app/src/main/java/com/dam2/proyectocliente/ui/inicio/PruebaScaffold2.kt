package com.dam2.proyectocliente.ui.inicio

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam2.proyectocliente.model.Actividad
import com.example.proyectocliente.ui.theme.AzulFondo

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PruebaScaffold2() {
    Scaffold(
        topBar = {
            Text(
                text = "Cabecera",
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxWidth()
            )
        },
        bottomBar = {
            Text(
                text = "pie",
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxWidth()
            )
        },
        snackbarHost = { Text(text = "snackbarHost")},
        floatingActionButton = { Button(onClick = { /*TODO*/ }) {
            Text(text = "Botón")
        }},
        floatingActionButtonPosition = FabPosition.Center,
        content = { a -> Contenido(a)}
        )
}

@Composable
fun Contenido(a: PaddingValues){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Cuerpo")
        Text(text = "Cuerpo")
        Text(text = "Cuerpo")
        Text(text = "Cuerpo")
        Text(text = "Cuerpo")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaActividadBusqueda0(a: Actividad) {
    val tam = 150.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RectangleShape,
            onClick = {/*TODO*/ }) {
            Image(
                painter = painterResource(id = a.imagen),
                contentDescription = a.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f),
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