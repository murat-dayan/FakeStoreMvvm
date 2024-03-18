package com.example.fakestoremvvm.domain.repository

import com.example.fakestoremvvm.core.common.Resource
import com.example.fakestoremvvm.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts():Flow<Resource<List<Product>>>

    fun getProductById(productId:Int): Flow<Resource<Product>>
}