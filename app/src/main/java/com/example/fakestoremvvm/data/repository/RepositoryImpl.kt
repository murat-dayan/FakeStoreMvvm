package com.example.fakestoremvvm.data.repository

import com.example.fakestoremvvm.core.common.Resource
import com.example.fakestoremvvm.data.remote.api.FakeStoreApi
import com.example.fakestoremvvm.data.remote.mapper.toDomainProduct
import com.example.fakestoremvvm.domain.model.Product
import com.example.fakestoremvvm.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val fakeStoreApi: FakeStoreApi
)  : ProductRepository{
    override fun getAllProducts(): Flow<Resource<List<Product>>> = flow{

        emit(Resource.Loading())

        val result = fakeStoreApi.getAllProducts().map {
            it.toDomainProduct()
        }
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

    override fun getProductById(productId: Int): Flow<Resource<Product>> = flow {

        emit(Resource.Loading())

        val resultProduct = fakeStoreApi.getProductById(productId).toDomainProduct()

        emit(Resource.Success(resultProduct))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }


}