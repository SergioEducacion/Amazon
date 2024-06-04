package com.example.amazon

import androidx.lifecycle.ViewModel
import com.example.amazon.data.DataSource
import com.example.amazon.data.ProductosUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AmazonViewModel: ViewModel()  {
    private val productos = DataSource.productos
    //private val tareasHoras= arrayListOf<TareaHoras>()
    private val _uiState = MutableStateFlow(ProductosUIState())
    val uiState: StateFlow<ProductosUIState> = _uiState.asStateFlow()
}