package com.example.fakestoremvvm.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.fakestoremvvm.R
import com.example.fakestoremvvm.databinding.FragmentProductDetailBinding


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)

        val bundle: ProductDetailFragmentArgs by navArgs()

        val id = bundle.productId
        println(id)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}