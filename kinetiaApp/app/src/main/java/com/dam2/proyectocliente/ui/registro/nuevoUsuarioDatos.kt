package com.dam2.proyectocliente.ui.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.ui.resources.TextFieldWithHeader
import com.dam2.proyectocliente.ui.resources.TextFieldEnterNumber
import com.dam2.proyectocliente.utils.fechaNacimientoOK
import com.dam2.proyectocliente.utils.texfieldVacio
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoUsuarioDatos(navController: NavHostController, vm: AppViewModel, estado: UiState) {

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido1 by rememberSaveable { mutableStateOf("") }
    var apellido2 by rememberSaveable { mutableStateOf("") }
    var diaT by rememberSaveable { mutableStateOf("") }
    var mesT by rememberSaveable { mutableStateOf("") }
    var anioT by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }

    var dia = diaT.toIntOrNull() ?: 0
    var mes = mesT.toIntOrNull() ?: 0
    var anio = anioT.toIntOrNull() ?: 0

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
                    if (texfieldVacio(arrayListOf(nombre, apellido1, diaT, mesT, anioT))
                        || !fechaNacimientoOK(dia, mes, anio)
                    ) {
                        error = true
                    } else {
                        vm.addCampoFormularioRegistro("nombre", nombre)
                        vm.addCampoFormularioRegistro("apellido1", apellido1)
                        vm.addCampoFormularioRegistro("apellido2", apellido2)
                        vm.addCampoFormularioRegistro("diaNac", diaT)
                        vm.addCampoFormularioRegistro("mesNac", mesT)
                        vm.addCampoFormularioRegistro("anioNac", anioT)
                        navController.navigate(Screens.addImagen.name)
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
                text = "Datos personales",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AzulAguaOscuro
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
                    .padding(40.dp)
            ) {

                TextFieldWithHeader(
                    cabecera = "Nombre",
                    value = nombre,
                    onValueChange = { nombre = it })
                TextFieldWithHeader(
                    cabecera = "Primer apellido",
                    value = apellido1,
                    onValueChange = { apellido1 = it })
                TextFieldWithHeader(
                    cabecera = "Segundo apellido",
                    value = apellido2,
                    onValueChange = { apellido2 = it })
                Text(
                    text = "Fecha de nacimiento",
                    color = NegroClaro,
                    fontSize = 16.sp
                )
                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextFieldEnterNumber(
                        label = "día",
                        value = diaT,
                        onValueChange = { diaT = it },
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 2.dp)
                    )
                    TextFieldEnterNumber(
                        label = "mes",
                        value = mesT,
                        onValueChange = { mesT = it },
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 2.dp)
                    )
                    TextFieldEnterNumber(
                        label = "año",
                        value = anioT,
                        onValueChange = { anioT = it },
                        modifier = Modifier.width(80.dp), ImeAction.Done
                    )
                }

                Text(text = estado.formularioRegistro.toString(), color = Color.Red)

            }

            when {
                error -> {
                    if (texfieldVacio(arrayListOf(nombre, apellido1, diaT, mesT, anioT)))
                        DialogInfo(
                            onConfirmation = { error = false },
                            dialogText = "Todos los campos son obligatorios"
                        )
                    else if (!fechaNacimientoOK(dia, mes, anio))
                        DialogInfo(
                            onConfirmation = { error = false },
                            dialogText = "Introduzca una fecha de nacimiento válida"
                        )

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    NuevoUsuarioDatos(navController, vm, estado)
}

