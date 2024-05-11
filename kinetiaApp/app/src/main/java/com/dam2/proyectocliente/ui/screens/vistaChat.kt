package com.dam2.proyectocliente.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.models.Message
import com.dam2.proyectocliente.utils.Picture
import com.example.proyectocliente.ui.theme.AzulFondo
import com.example.proyectocliente.ui.theme.AzulLogo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.Rojo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaChat(
    navController: NavHostController,
    chat: Chat,
    vm: AppViewModel,
    estado: UiState
) {

    Scaffold(
        topBar = { BarraSuperiorChat(navController, chat, vm) },
        content = { innerPadding -> ContenidoChat(innerPadding, chat) },
        bottomBar = { EntradaDeTexto(vm, estado) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorChat(
    navController: NavHostController, chat: Chat, vm: AppViewModel
) {
    var mostrarMenu by remember{ mutableStateOf(false) }
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(shape = CircleShape) {
                    Image(
                        painter = painterResource(id = Picture.getProfilePictureInt(chat.contactPicture)),
                        contentDescription = chat.contactName,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = chat.contactName)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                vm.mostrarPanelNavegacion()
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        },
        actions = {

            IconButton(onClick = {
                mostrarMenu = !mostrarMenu
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "menú chat"
                )
            }
            DropdownMenu(
                expanded = mostrarMenu,
                onDismissRequest = { mostrarMenu=false},
                modifier = Modifier.background(BlancoFondo)
            ) {
                DropdownMenuItem(
                    text = { Text(text = "borrar contacto", color = Rojo)},
                    onClick = { /*TODO*/ })
            }
        }
    )
}

@Composable
fun ContenidoChat(innerPadding: PaddingValues, chat: Chat) {
    LazyColumn(
        reverseLayout = true,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

    ) {
        items(chat.messages.reversed()) { m ->
            VistaMensaje(mensaje = m, chat.contactId)
        }
    }
}

@Composable
fun VistaMensaje(mensaje: Message, idContacto: Long) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 10.dp),
        horizontalArrangement = if (
            mensaje.recipient == idContacto) Arrangement.Start else Arrangement.End
    ) {

        Card(
            modifier = Modifier.widthIn(max = 300.dp),
            colors = CardDefaults.cardColors(AzulFondo)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = mensaje.sentAt.toString(), fontSize = 10.sp, textAlign = TextAlign.End)
                Text(text = mensaje.content)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntradaDeTexto(vm: AppViewModel, estado: UiState) {
    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 50.dp, max = 200.dp)
                .background(BlancoFondo)
                .padding(start = 12.dp, end = 12.dp, bottom = 4.dp, top = 4.dp)
        ) {

            OutlinedTextField(
                value = estado.mensajeEnviar,
                onValueChange = { vm.setMensaje(it) },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Default
                ),
                modifier = Modifier
                    .weight(.9f)
                    .background(Color.White)
                    .border(1.dp, Gris2, RoundedCornerShape(4.dp))
                    .heightIn(min = 40.dp)
            )
            IconButton(
                modifier = Modifier.weight(.1f),
                onClick = { vm.enviarMensaje()}
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "enviar",
                    tint = AzulLogo
                )
            }
        }
    }

}


/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun ChatPreview() {
//    val vm: AppViewModel = viewModel()
//    val navController = rememberNavController()
//    val a = DatosPrueba.cargarConversaciones()[0]
//    val estado by vm.uiState.collectAsState()
//    VistaChat(navController = navController, a, vm, estado)
}