package com.dam2.proyectocliente.ui.recursos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.NegroClaro


@Composable
fun DialogoInfo(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit,
    dialogTitle: String = "Atención",
    dialogText: String,
    icon: ImageVector = Icons.Default.Info,
    buttonConfirm: String = "Aceptar",
    buttonDismiss: String = ""
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = "Icon") },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest(); println("Confirmación") },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(buttonConfirm)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(buttonDismiss)
            }
        }
    )
}


/**
TEXFIELD
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldConCabecera(
    cabecera: String,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next
) {
    Text(
        text = cabecera,
        color = NegroClaro,
        fontSize = 16.sp
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction  //tipo de botón
        ),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
    )

    Spacer(modifier = Modifier.height(20.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldIntroducirNumero(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    anchoTF: Dp = 52.dp
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, color = NegroClaro, fontSize = 16.sp)
        Spacer(modifier = Modifier.width(2.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction //tipo de botón
            ),
            modifier = Modifier.width(anchoTF),
            colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
        )
    }
}
//TODO: poner colores
@Preview(showBackground = true)
@Composable
fun DialogoPreview() {
    DialogoInfo(
        onDismissRequest = {},
        onConfirmation = {},
        dialogTitle = "Atención",
        dialogText = "Esto es un mensaje",
//        icon = Icons.Default.Info,
        buttonConfirm = "Aceptar",
        buttonDismiss = "Denegar"
    )

}