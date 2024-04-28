import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.dam2.proyectocliente.model.Categoria
import com.example.proyectocliente.ui.theme.AzulAguaFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBox(
    labelText: @Composable (() -> Unit)? = null,
    options: ArrayList<String> = arrayListOf("Option 1", "Option 2", "Option 3"),
    onOptionChosen: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null, // Cambiar aquí
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedOption) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (options.isNotEmpty()) {
                expanded = !expanded
            }
        },
        modifier = modifier,
    ) {
        TextField(
            enabled = options.isNotEmpty(),
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedText ?: "", 
            onValueChange = {},
            label = labelText,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = AzulAguaFondo),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = option
                        onOptionChosen(option)
                        expanded = false
                    }, text = { Text(option) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxCategoria(
    labelText: String = "",
    options: ArrayList<Categoria>, //= arrayListOf(Categoria.Experiencias, Categoria.Ocio),
    onOptionChosen: (Categoria) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: Categoria? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategoria by remember { mutableStateOf(selectedOption) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (options.isNotEmpty()) {
                expanded = !expanded
            }
        },
        modifier = modifier
    ) {
        TextField(
            enabled = options.isNotEmpty(),
//            modifier = Modifier.menuAnchor(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            value = selectedCategoria?.toString() ?: "", // Usamos el nombre de la categoría como valor del TextField
            onValueChange = {},
//            label = { Text(text = labelText) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = AzulAguaFondo),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { categoria ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategoria = categoria
                        onOptionChosen(categoria)
                        expanded = false
                    },
                    text = {Text(categoria.toString())} // Mostramos el nombre de la categoría en el menú desplegable
                )
            }
        }
    }
}


