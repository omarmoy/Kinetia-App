package com.dam2.proyectocliente.ui.forms

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.utils.textFieldEmpty
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.ui.resources.TextFieldWithHeader
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAdvertisement(
    navController: NavHostController,
    vm: AppViewModel,
    advertisement: Advertisement
) {

    var title by rememberSaveable { mutableStateOf(advertisement.title) }
    var location by rememberSaveable { mutableStateOf(advertisement.location) }
    var content by rememberSaveable { mutableStateOf(advertisement.description) }

    var error by rememberSaveable { mutableStateOf(false) }
    val setError: (Boolean) -> Unit = { e -> error = e }

    Scaffold(
        topBar = { TopBarEditAD(navController, vm) },
        bottomBar = {
            BottomBarEditAd(
                vm, navController, title, location, content, setError, advertisement
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

                TextFieldWithHeader(
                    header = "Título",
                    value = title,
                    onValueChange = { title = it }
                )
                TextFieldWithHeader(
                    header = "Ubicación",
                    value = location,
                    onValueChange = { location = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
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
                    DialogInfo(
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
fun TopBarEditAD(navController: NavHostController, vm: AppViewModel) {
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
fun BottomBarEditAd(
    vm: AppViewModel,
    navController: NavHostController,
    title: String,
    location: String,
    content: String,
    setError: (Boolean) -> Unit,
    advertisement: Advertisement,
) {

    val campos = arrayListOf(title, location, content)

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
                if (textFieldEmpty(campos))
                    setError(true)
                else {
                    vm.editAdvertisement(title, location, content, advertisement)
                    navController.navigateUp()
                }

            }) {
                Text(text = "Guardar cambios", color = AzulAguaOscuro, fontSize = 16.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditAdPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val ad = Moker.advertisement
    EditAdvertisement(navController, vm, ad)
}