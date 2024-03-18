package com.example.fakestoremvvm.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fakestoremvvm.domain.use_case.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

}