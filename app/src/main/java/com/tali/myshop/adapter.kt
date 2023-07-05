package com.tali.myshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tali.myshop.databinding.ListitemsBinding

class adapter(var productList: List<Products>): RecyclerView.Adapter<ProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding=
            ListitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var current=productList.get(position)
        var binding=holder.binding
        binding.tvtitle.text=current.title
        binding.tvid.text=current.id.toString()
        binding.tvdes.text=current.description
        binding.tvprice.text=current.price.toString()
        binding.tvrating.text=current.rating.toString()
        binding.tvcate.text=current.category
        binding.tvstock.text=current.stock
        binding.mvthumb.tag=current.thumbnail

        Picasso
            .get()
            .load(current.thumbnail)
            .resize(80,80)
            .into(binding.mvthumb)
    }

}

class ProductViewHolder(var binding:ListitemsBinding): RecyclerView.ViewHolder(binding.root)