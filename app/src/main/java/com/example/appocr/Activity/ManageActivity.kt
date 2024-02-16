package com.example.appocr.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appocr.Adapter.ProductAdapter
import com.example.appocr.Model.Inventory
import com.example.appocr.R

class ManageActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)

        recyclerView = findViewById(R.id.rcv_product)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val productList = arrayListOf<Inventory>()
        productList.add(Inventory(1, "Tên mặt hàng : Iphone13", 20, "1000000"))
        productList.add(Inventory(2, "Tên mặt hàng : Iphone11", 80, "1200000"))
        productList.add(Inventory(3, "Tên mặt hàng : Iphone12", 10, "2000000"))
        productList.add(Inventory(4, "Tên mặt hàng : Iphone14", 40, "3000000"))
        productList.add(Inventory(5, "Tên mặt hàng : Iphone15", 30, "4000000"))
        productAdapter = ProductAdapter(productList = productList)
        recyclerView.adapter = productAdapter
    }
}