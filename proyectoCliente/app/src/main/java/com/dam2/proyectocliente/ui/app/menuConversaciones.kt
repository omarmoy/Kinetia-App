package com.dam2.proyectocliente.ui.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.Data.DatosPrueba
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Contacto
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.AzulLogo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuConversaciones(navController: NavHostController) {
    Scaffold(
        topBar = { BarraSuperiorConver(navController = navController) },
        content = { innerPadding -> Conversaciones(innerPadding) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorConver(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        Text(text = "Mensajes", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conversaciones(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(BlancoFondo)
        //.verticalScroll(rememberScrollState())
    ) {

        LazyColumn {
            item {
                TextField(
                    value = "",
                    onValueChange = { it },
                    singleLine = true,
                    label = { Text(text = "Buscar") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "buscar",
                            tint = NegroClaro
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(DatosPrueba.usuario.contactos) { c ->
                MiniaturaContacto(c)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniaturaContacto(c: Contacto) {
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(AzulFondo),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(75.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(shape = CircleShape) {
                Image(
                    painter = painterResource(id = c.foto),
                    contentDescription = c.nombre,
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = c.nombre, fontSize = 20.sp)
                if (c.mensajeNuevo) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "buscar",
                        tint = Rojo
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "buscar",
                        tint = NegroClaro
                    )
                }
            }
        }
    }

}


/**
 * VISTA PREVIA
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ConverPreview() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { BarraSuperiorConver(navController = navController) },
        content = { innerPadding -> Conversaciones(innerPadding) },
        //llama a una función de navegación:
        bottomBar = { PanelNavegacion(navController = navController) }
    )


    //MiniaturaContacto(c = DatosPrueba.usuario.contactos[0])
}