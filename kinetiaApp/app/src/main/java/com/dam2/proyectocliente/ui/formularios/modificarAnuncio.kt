package com.dam2.proyectocliente.ui.formularios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dam2.proyectocliente.utils.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.utils.texfieldVacio
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.ui.recursos.DialogoInfo
import com.dam2.proyectocliente.ui.recursos.TextFieldConCabecera
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificarAnuncio(
    navController: NavHostController,
    vm: AppViewModel, estado: UiState,
    advertisement: Advertisement
) {

    var titulo by rememberSaveable { mutableStateOf(advertisement.title) }
    var ubicacion by rememberSaveable { mutableStateOf(advertisement.location) }
    var contenido by rememberSaveable { mutableStateOf(advertisement.description) }

    var error by rememberSaveable { mutableStateOf(false) }
    val setError: (Boolean) -> Unit = { e -> error = e }

    Scaffold(
        topBar = { BarraSuperiorModAnuncio(navController, vm) },
        bottomBar = {
            BarraInferiorModAnuncio(
                vm, navController, titulo, ubicacion, contenido, setError, advertisement
            )
        }

    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            val padd = 40.dp
            Column(
                modifier = Modifier
                    .background(BlancoFondo)
                    .padding(start = padd, end = padd, top = 8.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldConCabecera(
                    cabecera = "Título",
                    value = titulo,
                    onValueChange = { titulo = it }
                )
                TextFieldConCabecera(
                    cabecera = "Ubicación",
                    value = ubicacion,
                    onValueChange = { ubicacion = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = contenido,
                    onValueChange = { contenido = it },
                    label = { Text("Descripción") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = AzulAguaOscuro),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                )


                //fin
                Spacer(modifier = Modifier.height(20.dp))
            }

            // control de entrada
            when {
                error -> {
                    DialogoInfo(
                        onConfirmation = { error = false },
                        dialogText = "Todos los campos son obligatorios"
                    )
                }
            }

        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorModAnuncio(navController: NavHostController, vm: AppViewModel) {
    TopAppBar(
        title = {
            Text(
                text = "Modificar Anuncio",
                modifier = Modifier.padding(start = 32.dp)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp(); vm.mostrarPanelNavegacion() }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "cancelar",
                    tint = AzulAguaOscuro
                )
            }
        }
    )
}

@Composable
fun BarraInferiorModAnuncio(
    vm: AppViewModel,
    navController: NavHostController,
    titulo: String,
    ubicacion: String,
    contenido: String,
    setError: (Boolean) -> Unit,
    advertisement: Advertisement,
) {

    val campos = arrayListOf<String>(titulo, ubicacion, contenido)

    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .background(BlancoFondo)
        ) {
            TextButton(onClick = {
                if (texfieldVacio(campos))
                    setError(true)
                else {
                    vm.modAnuncio(titulo, ubicacion, contenido, advertisement)
                    navController.navigateUp()
                }

            }) {
                Text(text = "Guardar cambios", color = AzulAguaOscuro, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ModificarAnuncioPreview() {
//    val navController = rememberNavController()
//    val vm: AppViewModel = viewModel()
//    val estado by vm.uiState.collectAsState()
//    val anuncio = DatosPrueba.anuncios[0]
//    ModificarAnuncio(navController, vm, estado, anuncio)
}