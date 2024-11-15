package com.dam2.proyectocliente.ui.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.NavigationPanel
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAgua
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsMenu(navController: NavHostController, vm: AppViewModel, uiState: UiState) {
    Scaffold(
        topBar = { TopBarChats(vm, uiState) },
        content = { innerPadding -> Chats(innerPadding, navController, vm, uiState) }
    )
}


@Composable
fun TopBarChats(vm: AppViewModel, uiState: UiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(BlancoFondo)
            .padding(12.dp)
    ) {
        val headerText =
            if (uiState.filterUnreadMessagesActive) "Mensajes no leídos" else "Mensajes"
        Text(text = headerText, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        IconButton(onClick = {
            if (uiState.filterUnreadMessagesActive)
                vm.quitarFiltroMensajesNoLeidos()
            else
                vm.filtrarMensajesNoleidos()
        }) {
            Icon(
                imageVector = Icons.Filled.List, contentDescription = "filtro",
                tint = if (uiState.filterUnreadMessagesActive) AmarilloPastel else AzulAgua
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chats(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {

        LazyColumn {
            item {
                TextField(
                    value = uiState.contactSearched,
                    onValueChange = { vm.setContactoBuscar(it) },
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
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = BlancoFondo,
                        unfocusedIndicatorColor = Gris2
                    ),
                    trailingIcon = {
                        if (uiState.contactSearched != "")
                            IconButton(onClick = { vm.setContactoBuscar("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "buscar",
                                    tint = NegroClaro
                                )
                            }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(vm.listaContactos()) { c ->
                Contact(c, navController, vm)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contact(c: Chat, navController: NavHostController, vm: AppViewModel) {
    Card(
        onClick = {
            vm.selectContact(c)
            vm.ocultarPanelNavegacion()
            navController.navigate(Screens.chat.name)
        },
        colors = CardDefaults.cardColors(BlancoFondo),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(75.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = BlancoFondo)
            ) {
                Image(
                    painter = painterResource(id=Painter.getProfilePictureInt(c.contactPicture)),
                    contentDescription = c.contactName,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(75.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = c.contactName, fontSize = 20.sp)
                if (c.newMessage) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "buscar",
                        tint = Rojo
                    )
                }
            }
        }
    }

}


/**
 * VISTA PREVIA
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ChatsPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    Scaffold(
        topBar = { TopBarChats(vm, uiState) },
        content = { innerPadding -> Chats(innerPadding, navController, vm, uiState) },
        //llama a una función de navegación:
        bottomBar = { NavigationPanel(navController = navController, vm, uiState) }
    )


//    MiniaturaContacto(c = Moker.user.chats[0])
}