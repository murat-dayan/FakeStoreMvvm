package com.example.fakestoremvvm.presentation.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fakestoremvvm.R
import com.example.fakestoremvvm.databinding.FragmentHomeBinding
import com.example.fakestoremvvm.presentation.adapters.ProductsAdapter
import com.example.fakestoremvvm.presentation.viewmodels.HomeFragmentViewModel
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsAdapter: ProductsAdapter

    private val homeFragmentViewModel by viewModels<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            homeFragmentViewModel.productState.collectLatest { state ->

                withContext(Dispatchers.Main){
                    if (state.products?.isNotEmpty()!!) {
                        println(state.products[1].title)
                    }
                    if (state.isLoading) {
                        // Sadece loading göstergesini gösterin
                        binding.progressId.visibility = View.VISIBLE
                        binding.homeFragProductRv.visibility = View.INVISIBLE
                    } else if (state.errorMsg.isNullOrEmpty()) {
                        // Products mevcutsa RecyclerView'ı gösterin
                        binding.progressId.visibility = View.INVISIBLE
                        binding.homeFragErrorText.visibility = View.INVISIBLE
                        binding.homeFragProductRv.visibility = View.VISIBLE
                        binding.homeFragProductRv.setHasFixedSize(true)
                        binding.homeFragProductRv.layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        productsAdapter = ProductsAdapter(requireContext(), state.products, findNavController())
                        binding.homeFragProductRv.adapter = productsAdapter
                    } else {
                        binding.progressId.visibility = View.INVISIBLE
                        binding.homeFragErrorText.visibility = View.VISIBLE
                        binding.homeFragProductRv.visibility = View.INVISIBLE
                        binding.homeFragErrorText.text = state.errorMsg.toString()
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