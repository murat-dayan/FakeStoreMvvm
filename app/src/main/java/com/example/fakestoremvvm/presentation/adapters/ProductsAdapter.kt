package com.example.fakestoremvvm.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.fakestoremvvm.R
import com.example.fakestoremvvm.databinding.CardViewProductBinding
import com.example.fakestoremvvm.domain.model.Product
import com.example.fakestoremvvm.presentation.fragments.HomeFragmentDirections

class ProductsAdapter(
    private val mContext: Context,
    private val products:List<Product>,
    private val navController: NavController
) : RecyclerView.Adapter<ProductsAdapter.CardViewHolder>(){
    inner class CardViewHolder(binding: CardViewProductBinding): RecyclerView.ViewHolder(binding.root) {

        var binding: CardViewProductBinding
        init {
            this.binding = binding
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInf = LayoutInflater.from(mContext)
        val binding = CardViewProductBinding.inflate(layoutInf,parent,false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val product = products[position]
        val cardBind = holder.binding
        cardBind.cardViewPriceId.text = product.price.toString()
        cardBind.cardViewRatingTextId.text = product.rating.toString()
        cardBind.cardViewTitleTextId.text = product.title

        Glide.with(mContext)
            .load(product.image)
            .into(cardBind.cardViewImageId)

        cardBind.cardProductViewId.setOnClickListener {
            val switch = HomeFragmentDirections.switchToHomeFragToProductDetailFrag(product.id)
            navController.navigate(switch)
        }

    }
}

