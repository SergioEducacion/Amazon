package com.example.amazon

import androidx.lifecycle.ViewModel
import com.example.amazon.data.DataSource
import com.example.amazon.data.Producto
import com.example.amazon.data.ProductoCantidad
import com.example.amazon.data.ProductosUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AmazonViewModel: ViewModel()  {
    private val productos = DataSource.productos
    private val productosCantidad= arrayListOf<ProductoCantidad>()
    private val _uiState = MutableStateFlow(ProductosUIState())
    val uiState: StateFlow<ProductosUIState> = _uiState.asStateFlow()

    fun addTareaCantidad(producto: Producto) {

        var productoCantidad=productosCantidad.firstOrNull({ it.producto==producto})
        if(productoCantidad==null){
            productoCantidad=ProductoCantidad(producto,0)
            productosCantidad.add(productoCantidad)
        }

        productoCantidad.cantidad++
        //var ultimaAccion="Se añade 1 hora a ${tareaHora.tarea.nombre}"
        //cambiarTodoUIState(ultimaAccion)
    }


    private fun cambiarSoloAccionUltimaUIState(texto: String) {
        _uiState.update { currentState ->
            currentState.copy(
                accionUltima = texto
            )
        }
    }
}