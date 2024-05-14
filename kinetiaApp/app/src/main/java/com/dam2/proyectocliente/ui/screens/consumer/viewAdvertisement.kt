package com.dam2.proyectocliente.ui.screens.consumer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.utils.showDate
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaFondo2
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaAnuncio(
    navController: NavHostController,
    advertisement: Advertisement,
    vm: AppViewModel,
    preview: Boolean = false
) {

    Scaffold(
        topBar = {
            if (preview)
                TopBarPreview()
            else
                TopBarAdvertisement(navController, advertisement, vm)
        },
        content = { innerPadding -> ContentAd(innerPadding, advertisement, vm) },
        bottomBar = {
            if (preview)
                BottomBarPreview(navController, vm)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarPreview() {
    TopAppBar(
        title = {
            Text(
                text = "Vista Previa",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "atrás"
            )
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarAdvertisement(
    navController: NavHostController, advertisement: Advertisement, vm: AppViewModel
) {
    TopAppBar(
        title = { },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                vm.mostrarPanelNavegacion()
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "atrás"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                vm.selectModAdvertisement(advertisement)
                navController.navigate(Screens.modificarAnuncio.name)
            }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "edit",
                    tint = AzulAguaOscuro
                )
            }
        }
    )
}

@Composable
fun ContentAd(innerPadding: PaddingValues, advertisement: Advertisement, vm: AppViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = BlancoFondo)
    ) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = AzulAguaFondo2),
            modifier = Modifier
                .size(150.dp)
        ) {
            Image(
                painter = painterResource(
                    id = Painter.getProfilePictureInt(vm.userCurrent()!!.profilePicture)
                ),
                contentDescription = advertisement.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = advertisement.userName, color = AzulAguaClaro, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = advertisement.title, color = AzulAguaOscuro, fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Text(
                text = "Ubicacion: " + advertisement.location,
                color = AzulAguaClaro,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Publicado " + showDate(
                    LocalDateTime.ofInstant(
                        advertisement.creationDate,
                        ZoneId.systemDefault()
                    )
                ),
                color = AzulAguaClaro,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

        Surface(
            modifier = Modifier
                .background(color = AmarilloPastel)
                .padding(top = 1.dp)
        ) {

            Text(
                text = advertisement.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlancoFondo)
                    .padding(16.dp)
            )


        }

    }
}


@Composable
fun BottomBarPreview(
    navController: NavHostController,
    vm: AppViewModel
) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(BlancoFondo)
        ) {
            TextButton(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Editar", color = AzulAguaOscuro, fontSize = 16.sp)
            }

            TextButton(onClick = {
                vm.resetNuevoAnuncio()
                vm.mostrarPanelNavegacion()
                navController.navigate(Screens.menuUsuario.name)
            }) {
                Text(text = "Cancelar", color = AzulAguaOscuro, fontSize = 16.sp)
            }
            TextButton(onClick = {
                vm.postAdvertisement()
                vm.mostrarPanelNavegacion()
                navController.navigate(Screens.menuUsuario.name)

            }) {
                Text(text = "Publicar", color = AzulAguaOscuro, fontSize = 16.sp)
            }
        }
    }

}

/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun AdPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val a = Moker.advertisement
    VistaAnuncio(navController, a, vm)
}