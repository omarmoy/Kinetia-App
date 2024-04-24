package com.dam2.proyectocliente.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.ui.Pantallas
import com.dam2.proyectocliente.ui.recursos.DialogoInfo
import com.dam2.proyectocliente.ui.recursos.TextFieldConCabecera
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddImagen(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var imagenSubida by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf(false) }

    val titulo = if (estado.esEmpresa) "Añade el logo de tu empresa" else "Sube una foto de perfil"
    val dialogText = if (estado.esEmpresa) "No has añadido el logo de tu empresa" else "No has subido una foto para tu perfil"
    val buttonConfirm = if (estado.esEmpresa) "Continuar sin logo" else "Continuar sin foto"
    val buttonDismiss = if (estado.esEmpresa) "Aladir logo" else "Elegir una foto"
    val imagePorDefecto = if(estado.esEmpresa) R.drawable.noimagen else R.drawable.nofoto

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
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlancoFondo)
            ) {
                TextButton(onClick = {
                    if (!imagenSubida) {
                        error = true
                    } else {
                        //TODO: añadir imagen
                        navController.navigate(Pantallas.confirmarRegistro.name)
                    }

                }) {
                    Text(text = "Siguiente", color = AzulAguaOscuro, fontSize = 16.sp)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = titulo,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AzulAguaOscuro
            )

            Spacer(modifier = Modifier.height(48.dp))

            Card(shape = CircleShape, modifier = Modifier.size(300.dp)) {
                Image(
                    painter = painterResource(id = imagePorDefecto),
                    contentDescription = estado.usuario!!.nombre,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            IconButton(onClick = {
            /*TODO*/
            }) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "addFoto",
                    tint = AzulAguaOscuro, modifier = Modifier.size(50.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
                    .padding(40.dp)
            ) {


            }

            when {
                error -> {
                    DialogoInfo(
                        onDismissRequest = { error = false },
                        onConfirmation = {
                            navController.navigate(Pantallas.confirmarRegistro.name)
                        },
                        dialogText = dialogText,
                        buttonConfirm = buttonConfirm,
                        buttonDismiss = buttonDismiss
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EERegistroPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    AddImagen(navController, vm, estado)
}

