package com.example.amazon.data

data class ProductosUIState(val accionAdd:String ="Ninguna", val accionRemove:String ="Ninguna",val productosEnCarrito:ArrayList<ProductoCantidad> = arrayListOf<ProductoCantidad>())