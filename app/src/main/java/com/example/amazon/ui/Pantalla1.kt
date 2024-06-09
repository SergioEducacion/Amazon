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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.amazon.AmazonViewModel
import com.example.amazon.data.Producto
import com.example.amazon.data.ProductosUIState

@Composable
fun Pantalla1(
    viewModelAmazon: AmazonViewModel,
    uiState: ProductosUIState,
    onClickCambiarPantalla: () -> Unit,
    productosOriginales: ArrayList<Producto>
) {


    Column() {
        Text(
            text = "AÑADIR PRODUCTOS AL CARRITO"
        )
        ProductosOriginalesToCarrito(Modifier, productosOriginales, viewModelAmazon)
        Text(
            text = "ACCIÓN AÑADIR"
        )
        Text(
            text = uiState.accionAdd
        )
        CambiarPantalla(onClickCambiarPantalla, Modifier)
    }
}




@Composable
private fun ProductosOriginalesToCarrito(
    modifier: Modifier,
    productosOriginales: ArrayList<Producto>,
    amazonViewModel: AmazonViewModel,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        items(productosOriginales) { producto ->
            Card(
                modifier = modifier
                    .padding(10.dp)

            ) {
                Text(
                    text = "Producto: ${producto.nombre}",
                    modifier = Modifier
                        .background(Color.Yellow)
                        .fillMaxWidth()
                        .padding(12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            amazonViewModel.addProductoToCarrito(
                                producto
                            )
                        }, modifier = Modifier
                    ) {
                        Text(text = "+")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CambiarPantalla(onClickCambiarPantalla: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClickCambiarPantalla, modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Ir a pantalla carrito")
    }
}