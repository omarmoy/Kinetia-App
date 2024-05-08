package com.dam2.proyectocliente.ui.registro

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.utils.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmarRegistro(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo)
            )
        }) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                //,verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(R.drawable.logoredondo),
                    contentDescription = "logotipo",
                    modifier = Modifier.height(120.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Enhorabuena",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroClaro
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "El registro se ha llevado a cabo con éxito",
                    textAlign = TextAlign.Center,
                    color = NegroClaro
                )


                Spacer(modifier = Modifier.height(180.dp))


                Button(
                    onClick = {
                        vm.mostrarPanelNavegacion()
                        navController.navigate(Pantallas.menuPrincipal.name)
                    },
                    colors = ButtonDefaults.buttonColors(AzulAguaOscuro),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ir a la aplicación")
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CRegistroPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    ConfirmarRegistro(navController, vm, estado)
}

