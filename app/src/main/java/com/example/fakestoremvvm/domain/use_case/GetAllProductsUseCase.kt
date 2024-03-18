package com.example.fakestoremvvm.domain.use_case

import com.example.fakestoremvvm.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke() = productRepository.getAllProducts()
}