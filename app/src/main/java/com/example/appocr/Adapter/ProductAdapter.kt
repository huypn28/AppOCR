package com.example.appocr.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appocr.Model.Inventory
import com.example.appocr.databinding.ItemProductBinding

class ProductAdapter(private val productList: List<Inventory>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = productList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Inventory) {
            binding.tvProductID.text = ""+data.id
            binding.tvProductName.text = data.ten
            binding.tvProductPrice.text = ""+data.gia
            binding.tvProductQuantity.text = ""+data.quantity
        }
    }
}
