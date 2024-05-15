package com.dam2.proyectocliente.ui.forms.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
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
import com.dam2.proyectocliente.ui.resources.LoandigDialogo
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooiceAvatar(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    var noAvatar by rememberSaveable { mutableStateOf(false) }
    var networkError by rememberSaveable { mutableStateOf(false) }
    var loandig by rememberSaveable { mutableStateOf(false) }
    var verify by rememberSaveable { mutableStateOf(false) }

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
                    if (uiState.selectedPicture == 0)
                        noAvatar = true
                    else{
                        loandig = true
                        verify = true
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
                text = "Elige un avatar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AzulAguaOscuro
            )

            Spacer(modifier = Modifier.height(48.dp))

            Card(shape = CircleShape, modifier = Modifier.size(300.dp)) {
                Image(
                    painter = painterResource(
                        id = if (uiState.selectedPicture == 0)
                            R.drawable.nofoto else uiState.selectedPicture
                    ),
                    contentDescription = uiState.user!!.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            IconButton(onClick = {
                navController.navigate(Screens.selectProfilePicture.name)
            }) {
                Icon(
                    imageVector = Icons.Filled.AddCircle, contentDescription = "addFoto",
                    tint = AzulAguaOscuro, modifier = Modifier.size(50.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
                    .padding(40.dp)
            ) {


            }

            when {
                noAvatar -> {
                    DialogInfo(
                        onDismissRequest = { noAvatar = false },
                        onConfirmation = {
                            loandig = true
                            verify = true
                        },
                        dialogText = "No has elegido avatar",
                        buttonConfirm = "Continuar sin avatar",
                        buttonDismiss = "Elegir avatar"
                    )
                }
            }
        }

        if(loandig){
            LoandigDialogo()
        }

        if(networkError){
            DialogInfo(
                onConfirmation = { networkError = false },
                dialogTitle = "Error de conexión",
                dialogText = "Inténtelo de nuevo más tarde"
            )
        }
        if(verify){
            LaunchedEffect(Unit) {
                val sigUpOk = vm.signUp()
                if (sigUpOk) {
                    loandig = false
                    verify = false
                    navController.navigate(Screens.confirmarRegistro.name)
                } else {
                    verify = false
                    loandig = false
                    networkError = true
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
    val uiState by vm.uiState.collectAsState()
    ChooiceAvatar(navController, vm, uiState)
}

