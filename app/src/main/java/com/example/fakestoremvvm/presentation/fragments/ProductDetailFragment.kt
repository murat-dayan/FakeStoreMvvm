package com.example.fakestoremvvm.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.fakestoremvvm.R
import com.example.fakestoremvvm.databinding.FragmentProductDetailBinding
import com.example.fakestoremvvm.presentation.adapters.ProductsAdapter
import com.example.fakestoremvvm.presentation.viewmodels.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding?=null
    private val binding get() = _binding!!

    private val productDetailViewModel by viewModels<ProductDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)

        val bundle: ProductDetailFragmentArgs by navArgs()

        val id = bundle.productId
        println(id)


        productDetailViewModel.getProductById(id)


        viewLifecycleOwner.lifecycleScope.launch {
            productDetailViewModel.productDetailState.collectLatest { state ->

                withContext(Dispatchers.Main) {


                    if (state.product != null) {
                        println(state.product.title)
                    }
                    if (state.isLoading) {
                        // Sadece loading göstergesini gösterin
                        binding.productDetailFragProgressId.visibility = View.VISIBLE
                        binding.productDetailFragImageId.visibility = View.INVISIBLE
                        binding.productDetailFragDescriptionTextId.visibility = View.INVISIBLE
                        binding.productDetailFragTitleTextId.visibility = View.INVISIBLE

                    } else if (state.product != null) {

                        binding.productDetailFragProgressId.visibility = View.INVISIBLE
                        binding.productDetailFragImageId.visibility = View.VISIBLE
                        binding.productDetailFragDescriptionTextId.visibility = View.VISIBLE
                        binding.productDetailFragTitleTextId.visibility = View.VISIBLE
                        binding.productDetailFragTitleTextId.text = state.product.title
                        binding.productDetailFragDescriptionTextId.text = state.product.description
                        Glide.with(requireContext())
                            .load(state.product.image)
                            .into(binding.productDetailFragImageId)
                    } else {
                        binding.productDetailFragImageId.visibility = View.INVISIBLE
                        binding.productDetailFragDescriptionTextId.visibility = View.INVISIBLE
                        binding.productDetailFragTitleTextId.visibility = View.INVISIBLE
                        binding.productDetailFragProgressId.visibility = View.INVISIBLE
                        binding.productDetailFragErrorTextId.visibility = View.VISIBLE
                        binding.productDetailFragErrorTextId.text = state.error
                        println(state.error)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}