package com.dam2.proyectocliente.ui.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
