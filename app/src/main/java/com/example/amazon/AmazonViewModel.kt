package com.example.amazon

import androidx.lifecycle.ViewModel
import com.example.amazon.data.Producto
import com.example.amazon.data.ProductoCantidad
import com.example.amazon.data.ProductosUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AmazonViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductosUIState())
    val uiState: StateFlow<ProductosUIState> = _uiState.asStateFlow()

    fun addProductoToCarrito(producto: Producto) {
        val productosCarrito:ArrayList<ProductoCantidad> = _uiState.value.productosEnCarrito
        //val productosCarrito:ArrayList<ProductoCantidad> = _uiState.value.productosEnCarrito.clone() as ArrayList<ProductoCantidad>
        var productoCarrito = productosCarrito.firstOrNull { it.producto == producto }
        if (productoCarrito == null) {
            productoCarrito = ProductoCantidad(producto, 0)
            productosCarrito.add(productoCarrito)
        }
        productoCarrito.cantidad++
        var ultimaAccion = "Se añade una unidad a ${productoCarrito.producto.nombre}"
        cambiarSoloAccionAddUIState(ultimaAccion)
    }
    private fun cambiarSoloAccionAddUIState(texto: String) {
        _uiState.update { currentState ->
            currentState.copy(
                accionAdd = texto
            )
        }
    }

    private fun cambiarSoloAccionRemoveUIState(texto: String) {
        _uiState.update { currentState ->
            currentState.copy(
                accionRemove = texto
            )
        }
    }

    private fun cambiarSoloProductosCarritoUIState(
        productosCarrito: ArrayList<ProductoCantidad>
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                productosEnCarrito = productosCarrito,
            )
        }
    }

    fun eliminarPrimerProductoDelCarrito() {
        //val productosCarrito:ArrayList<ProductoCantidad> = _uiState.value.productosEnCarrito.clone() as ArrayList<ProductoCantidad>
        val productosCarrito:ArrayList<ProductoCantidad> = _uiState.value.productosEnCarrito

        if (productosCarrito.size >= 1) {
            val pro=productosCarrito[0]
            productosCarrito.removeAt(0)
            var ultimaAccion = "Se ha eliminado ${pro.producto.nombre}"
            cambiarSoloAccionRemoveUIState(ultimaAccion)

        }
    }

    fun inicializarAccionEliminar() {
        cambiarSoloAccionRemoveUIState("Ninguna")
    }

    fun inicializarAccionAdd() {
        cambiarSoloAccionAddUIState("Ninguna")
    }
}