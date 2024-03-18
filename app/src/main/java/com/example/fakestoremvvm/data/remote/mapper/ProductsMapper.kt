package com.example.fakestoremvvm.data.remote.mapper

import com.example.fakestoremvvm.data.remote.dto.ProductDto
import com.example.fakestoremvvm.domain.model.Product

fun ProductDto.toDomainProduct():Product{
    return Product(
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating.rate,
        title = title
    )
}