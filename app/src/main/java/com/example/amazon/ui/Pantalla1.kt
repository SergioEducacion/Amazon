package com.example.amazon.ui

import androidx.compose.runtime.Composable
import com.example.amazon.AmazonViewModel
import com.example.amazon.data.ProductosUIState

@Composable
fun Pantalla1(
    viewModelAmazon: AmazonViewModel,
    uiState: ProductosUIState,
    onClickCambiarPantalla: () -> Unit
) {}