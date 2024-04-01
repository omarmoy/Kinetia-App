package com.dam2.proyectocliente.ui.inicio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.AzulAgua
import com.example.proyectocliente.ui.theme.NegroClaro


@Composable
fun Inicio(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        //,verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painter = painterResource(R.drawable.logoredondoletras),
            contentDescription = "logotipo",
            modifier = Modifier.height(120.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Encuéntralo en KINèTIA",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NegroClaro
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "La App donde publicar o buscar actividades cerca de ti",
            textAlign = TextAlign.Center,
            color = NegroClaro
        )


        Spacer(modifier = Modifier.height(180.dp))

        OutlinedButton(
            onClick = {},
            border = BorderStroke(ButtonDefaults.outlinedButtonBorder.width, color = AzulAgua),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registro", color = AzulAguaOscuro)
        }
        Button(
            onClick = {navController.navigate(Pantallas.login.name)},
            colors = ButtonDefaults.buttonColors(AzulAguaOscuro),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar sesión")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    val navController = rememberNavController()
    Inicio(navController)
}