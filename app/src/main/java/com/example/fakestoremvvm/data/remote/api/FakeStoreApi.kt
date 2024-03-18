package com.example.fakestoremvvm.data.remote.api

import com.example.fakestoremvvm.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProducts() : List<ProductDto>

    @GET("products/{productId}")
    suspend fun getProductById(@Path("id") productId:Int): ProductDto
}