package com.example.fakestoremvvm.presentation.state

import com.example.fakestoremvvm.domain.model.Product

data class ProductDetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
