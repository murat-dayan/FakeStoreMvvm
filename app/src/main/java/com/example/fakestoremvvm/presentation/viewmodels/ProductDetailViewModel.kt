package com.example.fakestoremvvm.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoremvvm.core.common.Resource
import com.example.fakestoremvvm.domain.model.Product
import com.example.fakestoremvvm.domain.use_case.GetAllProductsUseCase
import com.example.fakestoremvvm.domain.use_case.GetProductByIdiUseCase
import com.example.fakestoremvvm.presentation.state.ProductDetailState
import com.example.fakestoremvvm.presentation.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductByIdiUseCase: GetProductByIdiUseCase,
) : ViewModel() {

    private val _productDetailState = MutableStateFlow(ProductDetailState())
    val productDetailState : StateFlow<ProductDetailState>
        get() = _productDetailState


    init {

    }

    fun getProductById(productId:Int){
        getProductByIdiUseCase(productId).onEach {result->

            when(result){
                is Resource.Error -> {
                    _productDetailState.value = ProductDetailState().copy(error = result.msg)
                }
                is Resource.Loading -> {
                    _productDetailState.value = ProductDetailState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _productDetailState.value = ProductDetailState().copy(product = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }

}