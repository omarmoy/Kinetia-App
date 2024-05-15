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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.ui.resources.LoandigDialogo
import com.dam2.proyectocliente.utils.isEmailValid
import com.dam2.proyectocliente.utils.arePasswordsSame
import com.dam2.proyectocliente.utils.textFieldEmpty
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMailPassword(navController: NavHostController, vm: AppViewModel, uiState: UiState) {

    var mail by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }
    var emailAlreadyExists by rememberSaveable { mutableStateOf(false) }
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
                    if (textFieldEmpty(arrayListOf(mail, password, repeatPassword))
                        || !isEmailValid(mail)
                        || !arePasswordsSame(password, repeatPassword)
                    ) {
                        error = true
                    } else {
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


            Spacer(modifier = Modifier.height(90.dp))
            Image(
                painter = painterResource(R.drawable.logoredondo),
                contentDescription = "logotipo",
                modifier = Modifier.height(120.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
            ) {
                Text(
                    text = "Email",
                    textAlign = TextAlign.Center,
                    color = NegroClaro,
                    fontSize = 16.sp
                )

                TextField(
                    value = mail,
                    onValueChange = { mail = it },
                    singleLine = true,
                    label = { Text(text = "Introduce tu correo electrónico") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Contraseña",
                    textAlign = TextAlign.Center,
                    color = NegroClaro,
                    fontSize = 16.sp
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    label = { Text(text = "Introduce una contraseña") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
                )

                TextField(
                    value = repeatPassword,
                    onValueChange = { repeatPassword = it },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    label = { Text(text = "Repite la contraseña") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done  //tipo de botón
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
                )
            }

            when {
                error -> {
                    if (textFieldEmpty(arrayListOf(mail, password, repeatPassword)))
                        DialogInfo(
                            onConfirmation = { error = false },
                            dialogText = "Todos los campos son obligatorios"
                        )
                    else if (!isEmailValid(mail))
                        DialogInfo(
                            onConfirmation = { error = false },
                            dialogText = "El email introducido no es válido"
                        )
                    else if (!arePasswordsSame(password, repeatPassword))
                        DialogInfo(
                            onConfirmation = { error = false },
                            dialogText = "Las contraseñas introducidas no son iguales"
                        )

                }
            }

            if(loandig){
                LoandigDialogo()
            }

            if(emailAlreadyExists){
                DialogInfo(
                    onConfirmation = { emailAlreadyExists = false },
                    dialogTitle = "Email ya registrado",
                    dialogText = "Inicie sesión o pruebe con otro"
                )
            }

            if(verify){
                LaunchedEffect(Unit) {
                    val exist = vm.verifyField(mail)
                    if (!exist) {
                        vm.addFieldFormSignUp("mail", mail)
                        vm.addFieldFormSignUp("password", password)
                        loandig = false
                        verify = false
                        if (uiState.isCompany && uiState.formSignUp["rol"] == Role.PROVIDER.toString())
                            navController.navigate(Screens.nuevaEmpresaDatos.name)
                        else
                            navController.navigate(Screens.nuevoUsuarioDatos.name)
                    } else {
                        verify = false
                        loandig = false
                        emailAlreadyExists = true
                    }
                }
            }
            

        }
    }
}


@Preview(showBackground = true)
@Composable
fun EmailPasswordPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    FormMailPassword(navController, vm, uiState)
}

