package com.example.amazon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amazon.data.DataSource
import com.example.amazon.ui.Pantalla1
import com.example.amazon.ui.Pantalla2
import com.example.amazon.ui.theme.AmazonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

enum class PrincipalScreen(@StringRes val title: Int) {
    Pantalla1(title = R.string.p1),
    Pantalla2(title = R.string.p2),
}

@Composable
fun Principal(navController: NavHostController = rememberNavController()) {
    val viewModelAmazon: AmazonViewModel = viewModel()
    val uiState by viewModelAmazon.uiState.collectAsState()
    var productosOriginales = DataSource.productos
    NavHost(
        navController = navController,
        startDestination = PrincipalScreen.Pantalla1.name,
    ) {
        composable(route = PrincipalScreen.Pantalla1.name) {
            Pantalla1(
                viewModelAmazon = viewModelAmazon,
                uiState = uiState,
                productosOriginales=productosOriginales,
                onClickCambiarPantalla={ viewModelAmazon.inicializarAccionEliminar();
                    navController.navigate(PrincipalScreen.Pantalla2.name) }
            )
        }
        composable(route = PrincipalScreen.Pantalla2.name) {
            Pantalla2(  viewModelAmazon = viewModelAmazon,
                uiState = uiState,
                onClickCambiarPantalla={
                    viewModelAmazon.inicializarAccionAdd();
                    navController.navigate(PrincipalScreen.Pantalla1.name) }
            )
        }
    }
}

