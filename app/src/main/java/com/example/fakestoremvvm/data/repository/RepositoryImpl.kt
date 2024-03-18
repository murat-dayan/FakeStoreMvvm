package com.example.fakestoremvvm.data.repository

import com.example.fakestoremvvm.data.remote.api.FakeStoreApi
import com.example.fakestoremvvm.domain.model.Product
import com.example.fakestoremvvm.domain.repository.ProductRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val fakeStoreApi: FakeStoreApi
)  : ProductRepository{
    override fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getProductById(productId: Int): Product {
        TODO("Not yet implemented")
    }
}