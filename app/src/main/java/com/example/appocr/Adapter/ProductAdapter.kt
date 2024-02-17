package com.example.appocr.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appocr.Activity.EditProductActivity
import com.example.appocr.Model.Inventory
import com.example.appocr.database.SQLiteHelper
import com.example.appocr.databinding.ItemProductBinding

class ProductAdapter(private var productList: List<Inventory>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

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

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {


        init {
            // Set click listener for the edit icon in the constructor
            binding.imageViewEdit.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, EditProductActivity::class.java)
                // Pass the product details to the EditProductActivity if needed
                intent.putExtra("productId", productList[adapterPosition].id)
                intent.putExtra("productName", productList[adapterPosition].ten)
                intent.putExtra("productQuantity", productList[adapterPosition].quantity)
                intent.putExtra("productPrice", productList[adapterPosition].gia)
                context.startActivity(intent)
            }
            binding.imageViewDelete.setOnClickListener {
                val productId = productList[adapterPosition].id
                deleteProduct(binding.root.context, productId)
            }
        }


        fun bind(data: Inventory) {
            binding.tvProductID.text = "" + data.id
            binding.tvProductName.text = data.ten
            binding.tvProductPrice.text = "" + data.gia
            binding.tvProductQuantity.text = "" + data.quantity
        }
    }
    fun updateProductList(newList: List<Inventory>) {
        productList = newList
        notifyDataSetChanged()
    }
    private fun deleteProduct(context: Context, productId: Int) {
        val dbHelper = SQLiteHelper(context)
        val rowsAffected = dbHelper.delete(productId)

        if (rowsAffected > 0) {
            val updatedList = productList.toMutableList()
            val removedProduct = updatedList.find { it.id == productId }
            updatedList.remove(removedProduct)
            updateProductList(updatedList)

            Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Xóa sản phẩm không thành công. Vui lòng thử lại.", Toast.LENGTH_SHORT).show()
        }
    }
}
