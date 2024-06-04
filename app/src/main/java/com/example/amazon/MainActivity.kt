package com.example.amazon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val viewModelTareas: AmazonViewModel = viewModel()
    val uiState by viewModelTareas.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = PrincipalScreen.Pantalla1.name,
        //modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = PrincipalScreen.Pantalla1.name) {
            PantallaTareas(
                tareaViewModel = viewModelTareas,
                uiState = uiState,
                onClickCambiarPantalla = { navController.navigate(PrincipalScreen.Pantalla2.name) })
        }
        composable(route = PrincipalScreen.Pantalla2.name) {
            PantallaVacia()
        }
    }
}

