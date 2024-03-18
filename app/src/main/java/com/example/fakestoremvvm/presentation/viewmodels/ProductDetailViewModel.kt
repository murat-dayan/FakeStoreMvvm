package com.example.fakestoremvvm.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fakestoremvvm.core.common.Resource
import com.example.fakestoremvvm.domain.model.Product
import com.example.fakestoremvvm.domain.use_case.GetAllProductsUseCase
import com.example.fakestoremvvm.domain.use_case.GetProductByIdiUseCase
import com.example.fakestoremvvm.presentation.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class ProductDetailViewModel(
    private val getProductByIdiUseCase: GetProductByIdiUseCase
) : ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState : StateFlow<ProductState>
        get() = _productState


    init {

    }



}