package com.dam2.proyectocliente.ui.menus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.menus.consumer.Title
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.AzulAgua
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteUser(navController: NavHostController, vm: AppViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = BlancoFondo
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        vm.mostrarPanelNavegacion()
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "volver",
                            tint = AzulAguaOscuro
                        )
                    }
                })
        }) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
                    .padding(40.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "buscar",
                    tint = Rojo,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "ATENCIÓN",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroClaro
                )

                Spacer(modifier = Modifier.height(10.dp))

                Title(texto = "Si borrar tu cuenta de usuario no la podrás recuperar")

                Spacer(modifier = Modifier.height(140.dp))

                OutlinedButton(
                    onClick = {
                        vm.deleteUser()
                        navController.navigate(Screens.inicio.name)
                    },
                    border = BorderStroke(
                        ButtonDefaults.outlinedButtonBorder.width,
                        color = AzulAgua
                    )
                ) {
                    Text(text = "Borrar usuario", color = AzulAguaOscuro)
                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    DeleteUser(navController, vm)
}