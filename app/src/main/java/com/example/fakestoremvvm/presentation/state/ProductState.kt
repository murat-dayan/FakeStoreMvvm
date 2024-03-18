package com.example.fakestoremvvm.presentation.state

import com.example.fakestoremvvm.domain.model.Product

data class ProductState(
    val products: List<Product>? = emptyList(),
    val isLoading : Boolean = false,
    val errorMsg: String?= ""
)
