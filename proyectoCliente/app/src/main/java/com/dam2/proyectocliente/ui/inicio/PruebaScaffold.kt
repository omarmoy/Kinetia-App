package com.dam2.proyectocliente.ui.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PruebaScaffold() {
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
        floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {
            Text(text = "Botón")
        }},
        floatingActionButtonPosition = FabPosition.Center,
        ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Cuerpo")
            Text(text = "Cuerpo")
            Text(text = "Cuerpo")
            Text(text = "Cuerpo")
            Text(text = "Cuerpo")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PruebaPreview() {
    PruebaScaffold()
}