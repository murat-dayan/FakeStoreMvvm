package com.example.fakestoremvvm.domain.use_case

import com.example.fakestoremvvm.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductById @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(productId:Int) = productRepository.getProductById(productId)
}