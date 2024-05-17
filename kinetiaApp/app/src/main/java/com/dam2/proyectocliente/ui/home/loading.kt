package com.dam2.proyectocliente.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "cargando"
    )
}


@Composable
fun ErrorScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(
            text = "Ha ocurrido un error. Compruebe sus credenciales y/o su conexión",
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                navController.navigateUp()
            },
            colors = ButtonDefaults.buttonColors(AzulAguaOscuro),
            modifier = Modifier.width(150.dp)
        ) {
            Text(text = "volver")
        }
    }
}