package com.dam2.proyectocliente.ui.resources

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectocliente.ui.theme.AzulAguaFondo
import com.example.proyectocliente.ui.theme.BlancoFondOscuro
import com.example.proyectocliente.ui.theme.NegroClaro
import com.example.proyectocliente.ui.theme.Rojo



@Composable
fun DialogInfo(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit,
    dialogTitle: String = "Atención",
    dialogText: String,
    icon: ImageVector = Icons.Default.Info,
    buttonConfirm: String = "Aceptar",
    buttonDismiss: String = ""
) {

    AlertDialog(
        icon = { Icon(icon, contentDescription = "Icon", tint = Rojo) },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        containerColor = BlancoFondOscuro,
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(text = buttonConfirm, color = NegroClaro)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = buttonDismiss, color = NegroClaro)
            }
        }
    )
}


/**
TEXFIELD
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithHeader(
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
fun TextFieldEnterNumber(
    label: String = "label",
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    imeAction: ImeAction = ImeAction.Next,
    showLabel: Boolean = true
) {
    if (showLabel) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            label = { Text(text = label, fontSize = 12.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction //tipo de botón
            ),
            modifier = modifier,
            colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
        )
    } else {
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction //tipo de botón
            ),
            modifier = modifier,
            colors = TextFieldDefaults.textFieldColors(containerColor = AzulAguaFondo)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogoPreview() {
    DialogInfo(
        onDismissRequest = {},
        onConfirmation = {},
        dialogTitle = "Atención",
        dialogText = "Esto es un mensaje",
//        icon = Icons.Default.Info,
        buttonConfirm = "Aceptar",
        buttonDismiss = "Denegar"
    )

}