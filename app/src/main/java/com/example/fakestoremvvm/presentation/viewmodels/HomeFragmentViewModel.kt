package com.example.fakestoremvvm.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoremvvm.core.common.Resource
import com.example.fakestoremvvm.domain.use_case.GetAllProductsUseCase
import com.example.fakestoremvvm.presentation.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState : StateFlow<ProductState>
        get() = _productState


    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        getAllProductsUseCase().onEach {result->

            when(result){
                is Resource.Error -> {
                    _productState.value = ProductState().copy(errorMsg = result.msg)
                }
                is Resource.Loading -> {
                    _productState.value = ProductState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _productState.value = ProductState().copy(products = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }

}