package com.dam2.proyectocliente.ui.inicio

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.utils.AppViewModel
import com.dam2.proyectocliente.ui.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorLogin(navController: NavHostController, vm: AppViewModel, estado: UiState) {
    //TODO:
}


@Preview(showBackground = true)
@Composable
fun ELoginPreview() {val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val estado by vm.uiState.collectAsState()
    ErrorLogin(navController, vm, estado)
}