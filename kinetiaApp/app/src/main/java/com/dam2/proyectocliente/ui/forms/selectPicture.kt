package com.dam2.proyectocliente.ui.forms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPicture(
    navController: NavHostController,
    vm: AppViewModel,
    pictures: ArrayList<Int>
) {

    Scaffold(
        topBar = { TopBarSelectPicture(navController, vm) },

    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            val padd = 40.dp
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = padd, end = padd, top = 8.dp)
//                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                LazyColumn{
                    items(pictures) { picture ->
                        Image(picture, navController, vm)
                    }
                }

            }


        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Image(image: Int, navController: NavHostController, vm: AppViewModel,) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors= CardDefaults.cardColors(containerColor = BlancoFondo),
        onClick = {
            println("imagen seleccionada: $image")
            vm.setPicture(image)
            navController.navigateUp()
        }
        ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(194.dp),
            contentScale = ContentScale.Crop

        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSelectPicture(navController: NavHostController, vm: AppViewModel) {
    TopAppBar(
        title = {
            Text(
                text = "Elige una imagen",
                modifier = Modifier.padding(start = 32.dp)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp(); vm.mostrarPanelNavegacion() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "atrás",
                    tint = AzulAguaOscuro
                )
            }
        }
    )
}




@Preview(showBackground = true)
@Composable
fun SelectPicturePreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
//    val estado by vm.uiState.collectAsState()
val images = Painter.profilePictures
//val images = Images.activityPicture
    SelectPicture(navController, vm, images)
}