package com.dam2.proyectocliente.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class PrincipalScreen(val title: String) {
    Pantalla1(title = "p1"),
    Pantalla2(title = "p2"),
    Pantalla3(title = "p3")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorTopAppBar(
    currentScreen: PrincipalScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "atras"
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalSergio(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = PrincipalScreen.valueOf(
        backStackEntry?.destination?.route ?: PrincipalScreen.Pantalla1.name
    )
    Scaffold(
        topBar = {
            BarraSuperiorTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PrincipalScreen.Pantalla1.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PrincipalScreen.Pantalla1.name) {
                Column {
                    Text(text = "Soy pantalla1")
                    Button(onClick =
                    { navController.navigate(PrincipalScreen.Pantalla2.name) }

                    ) {
                        Text(text = "Te llevo a pantalla 2")
                    }
                    Button(onClick =  { navController.navigate(PrincipalScreen.Pantalla3.name) }
                    ) {
                        Text(text = "Te llevo a pantalla 3")
                    }
                }

            }
            composable(route = PrincipalScreen.Pantalla2.name) {
                Text(text = "Pantalla2")
            }
            composable(route = PrincipalScreen.Pantalla3.name) {
                Text(text = "Pantalla1")
            }

        }
    }
}