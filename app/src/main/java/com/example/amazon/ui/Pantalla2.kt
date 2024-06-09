package com.example.amazon.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.amazon.AmazonViewModel
import com.example.amazon.data.Producto
import com.example.amazon.data.ProductosUIState

@Composable
fun Pantalla2(
    viewModelAmazon: AmazonViewModel,
    uiState: ProductosUIState,
    onClickCambiarPantalla: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "CARRITO")
        ProductosCarrito(Modifier, uiState)
        Text(text = "ACCIÓN ELIMINAR")
        Text(text = uiState.accionRemove)
        EliminarProductoDelCarrito(viewModelAmazon, Modifier)
        CambiarPantalla(onClickCambiarPantalla, Modifier)

    }
}


@Composable
private fun ProductosCarrito(
    modifier: Modifier,
    uiState: ProductosUIState,
) {
    var productosEnCarrito = uiState.productosEnCarrito
    var colorLetra = Color.Yellow;
    var colorYellow = true;
    for (p in productosEnCarrito) {

        colorYellow = !colorYellow
        if (colorYellow) {
            colorLetra = Color.Yellow;
        } else {
            colorLetra = Color.Cyan;
        }
        Text(
            text = "Producto: ${p.producto.nombre}, unidades: ${p.cantidad}",
            modifier = modifier
                .background(colorLetra)
                .fillMaxWidth()
        )
    }
}

@Composable
fun EliminarProductoDelCarrito(viewModelAmazon: AmazonViewModel, modifier: Modifier) {
    Button(
        onClick = { viewModelAmazon.eliminarPrimerProductoDelCarrito() },
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Eliminar primer producto del carrito")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CambiarPantalla(onClickCambiarPantalla: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClickCambiarPantalla,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Ir a pantalla productos originales")
    }
}