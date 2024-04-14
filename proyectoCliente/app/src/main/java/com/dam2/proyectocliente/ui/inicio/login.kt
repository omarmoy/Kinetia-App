package com.dam2.proyectocliente.ui.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.ui.Pantallas
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = BlancoFondo
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "volver",
                            //modifier = Modifier.shadow(elevation = 8.dp, shape = Shape.)
                            tint = AzulAguaOscuro
                        )
                    }
                })
        }) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlancoFondo)
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Inicio de sesión",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroClaro
                )

                Spacer(modifier = Modifier.height(40.dp))
                TextField(
                    value = "",
                    onValueChange = { it },
                    singleLine = true,
                    label = { Text(text = "introduce tu correo electrónico") },
                    //leadingIcon = { Icon(painter = painterResource(id = R.drawable.money), contentDescription = null) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = "",
                    onValueChange = { it },
                    singleLine = true,
                    label = { Text(text = "introduce la contraseña") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
                )
                /*OutlinedTextField(
                    value = "",
                    onValueChange = { it },
                    singleLine = true,
                    label = { Text(text = "introduce la contraseña") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
*/
                Spacer(modifier = Modifier.height(180.dp))

                Button(
                    onClick = {
                        //TODO
                        vm.mostrarPanelNavegacion()
                        navController.navigate(Pantallas.menuPrincipal.name)
                    },
                    colors = ButtonDefaults.buttonColors(AzulAguaOscuro),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "Aceptar")
                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    Login(navController, vm, estado)
}