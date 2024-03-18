package com.example.fakestoremvvm.domain.repository

import com.example.fakestoremvvm.domain.model.Product

interface ProductRepository {

    fun getAllProducts():List<Product>

    fun getProductById(productId:Int): Product
}