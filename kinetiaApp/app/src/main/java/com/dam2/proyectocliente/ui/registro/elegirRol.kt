package com.dam2.proyectocliente.ui.registro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.model.Rol
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.AzulAgua
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElegirRol(navController: NavHostController, vm: AppViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "volver",
                            tint = AzulAguaOscuro
                        )
                    }
                }
            )
        },
    ) { padding ->
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
                Spacer(modifier = Modifier.height(90.dp))

                Text(
                    text = "Cuéntamos, ¿cómo vas a utilizar la aplicación?",
                    textAlign = TextAlign.Center,
                    color = NegroClaro
                )
                Spacer(modifier = Modifier.height(24.dp))

                OutlinedButton(
                    onClick = {
                        vm.setEsEmpresa(false)
                        vm.addCampoFormularioRegistro("rol", Rol.CONSUMIDOR.toString())
                        navController.navigate(Pantallas.nuevoUsuario.name)
                    },
                    border = BorderStroke(
                        ButtonDefaults.outlinedButtonBorder.width,
                        color = AzulAgua
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Quiero encontrar actividades", color = AzulAguaOscuro)
                }

                Spacer(modifier = Modifier.height(24.dp))
                OutlinedButton(
                    onClick = {
                        vm.addCampoFormularioRegistro("rol", Rol.OFERTANTE.toString())
                        navController.navigate(Pantallas.elegirTipoPro.name)
                    },
                    border = BorderStroke(
                        ButtonDefaults.outlinedButtonBorder.width,
                        color = AzulAgua
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Soy ofertante de actividades", color = AzulAguaOscuro)
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ElegirRolPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    ElegirRol(navController, vm)
}

