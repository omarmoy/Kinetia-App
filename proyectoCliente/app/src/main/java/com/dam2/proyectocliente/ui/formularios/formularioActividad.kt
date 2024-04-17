package com.dam2.proyectocliente.ui.formularios

import ComboBox
import ComboBoxCategoria
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.controlador.AppViewModel
import com.dam2.proyectocliente.controlador.UiState
import com.dam2.proyectocliente.model.Categoria
import com.dam2.proyectocliente.ui.recursos.TextFieldConCabecera
import com.dam2.proyectocliente.ui.recursos.TextFieldIntroducirNumero
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioActividad(
    navController: NavHostController = rememberNavController(),
    vm: AppViewModel = viewModel()
) {
    val estado by vm.uiState.collectAsState()


    val categorias: ArrayList<Categoria> =
        ArrayList(Categoria.values().filter { it != Categoria.Todo })
    val listaHoras = arrayListOf<String>(
        "00",
        "01",
        "02",
        "04",
        "05",
        "06",
        "07",
        "08",
        "09",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        "16",
        "17",
        "18",
        "19",
        "20",
        "21",
        "22",
        "23"
    )
    val listaMinutos = arrayListOf<String>("00", "15", "30", "45")

    var titulo by rememberSaveable { mutableStateOf("") }
    var precioT by rememberSaveable { mutableStateOf("") }
    var ubicacion by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf<Categoria?>(null) }
    var destacado by rememberSaveable { mutableStateOf(false) }
    var contenido by rememberSaveable { mutableStateOf("") }
    var diaT by rememberSaveable { mutableStateOf("") }
    var mesT by rememberSaveable { mutableStateOf("") }
    var anioT by rememberSaveable { mutableStateOf(LocalDate.now().year.toString()) }
    var horaT by rememberSaveable { mutableStateOf("") }
    var minutosT by rememberSaveable { mutableStateOf("") }

    var dia = diaT.toIntOrNull() ?: 0
    var mes = mesT.toIntOrNull() ?: 0
    var anio = anioT.toIntOrNull() ?: 0
    var hora = horaT.toIntOrNull() ?: -1
    var minutos = minutosT.toIntOrNull() ?: -1
    var precio = precioT.toDoubleOrNull() ?: 0.0


    Scaffold(
        topBar = { BarraSuperiorFA(navController) },
        bottomBar = { BarraInferiorFA(vm, estado, categoria) }

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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.noimagen),
                        contentDescription = "imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                /*TODO*/
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = "addImagen",
                                tint = AzulAguaOscuro
                            )
                        }
                    }

                }

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

                Text(text = "Categoria", color = NegroClaro, fontSize = 16.sp)
                ComboBoxCategoria(
                    options = categorias,
                    onOptionChosen = { categoria = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Fecha y hora de inicio", color = NegroClaro, fontSize = 16.sp)
                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextFieldIntroducirNumero(
                        label = "día",
                        value = diaT,
                        onValueChange = { diaT = it },
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 2.dp)
                    )
                    TextFieldIntroducirNumero(
                        label = "mes",
                        value = mesT,
                        onValueChange = { mesT = it },
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 2.dp)
                    )
                    TextFieldIntroducirNumero(
                        label = "año",
                        value = anioT,
                        onValueChange = { anioT = it },
                        modifier = Modifier.width(80.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {

//                    Spacer(modifier = Modifier.width(2.dp))
                    ComboBox(
                        labelText = { Text(text = "hora", fontSize = 12.sp) },
                        options = listaHoras,
                        onOptionChosen = { horaT = it },
                        modifier = Modifier.width(95.dp)
                    )

                    Spacer(modifier = Modifier.width(2.dp))
                    ComboBox(
                        labelText = { Text(text = "min.", fontSize = 12.sp) },
                        options = listaMinutos,
                        onOptionChosen = { minutosT = it },
                        modifier = Modifier.width(95.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = contenido,
                    onValueChange = { contenido = it },
                    label = { Text("Descripción") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = AzulAguaOscuro),
                    modifier = Modifier.fillMaxWidth().height(500.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorFA(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Formulario Actividad") },
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
}

@Composable
fun BarraInferiorFA(
    vm: AppViewModel,
    estado: UiState,
    categoria: Categoria?,
) {

    var error by rememberSaveable { mutableStateOf(false) }
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

                //TODO

            }) {
                Text(text = "Vista previa", color = AzulAguaOscuro, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MenuInicioPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    FormularioActividad(navController, vm)
}