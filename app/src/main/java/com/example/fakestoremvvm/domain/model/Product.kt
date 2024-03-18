package com.example.fakestoremvvm.domain.model

data class Product(
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Double,
    val title: String,
)