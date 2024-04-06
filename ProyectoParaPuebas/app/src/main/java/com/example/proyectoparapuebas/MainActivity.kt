package com.example.proyectoparapuebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectoparapuebas.ui.theme.ProyectoParaPuebasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoParaPuebasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FocusOnButtonClick()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusOnButtonClick() {
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Write something...") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusRequester.freeFocus() }),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Hacemos que el TextField obtenga el foco cuando se hace clic en el botón
                focusRequester.requestFocus()
            }
        ) {
            Text("Focus on TextField")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoParaPuebasTheme {
    }
}

/*@Composable
fun MyScreenContent() {
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    // Controla si el TextField debe estar enfocado o no
    var isFocused by remember { mutableStateOf(false) }

    // Maneja el evento de clic en cualquier parte de la pantalla para desenfocar el TextField
    val onClickOutside: () -> Unit = {
        isFocused = false
        focusRequester.requestFocus() // Solicita el foco para otro elemento cuando se hace clic en otro lugar
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Esto hace que el TextField pierda el foco y el cursor parpadeante no esté visible
            isFocused = false
            focusRequester.requestFocus() // Solicita el foco para otro elemento (por ejemplo, un botón) cuando se hace clic en él
        }) {
            Text("Click me to unfocus TextField")
        }
    }

    // Observa los clics fuera del TextField
    LaunchedEffect(Unit) {
        val parentLayout = (LocalContext.current as? AppCompatActivity)?.findViewById<ViewGroup>(android.R.id.content)
        parentLayout?.setOnClickListener {
            onClickOutside()
        }
    }
}*/
