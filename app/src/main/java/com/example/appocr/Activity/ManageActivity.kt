package com.example.appocr.Activity


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appocr.Activity.AddProductActivity
import com.example.appocr.Adapter.ProductAdapter
import com.example.appocr.Model.Inventory
import com.example.appocr.R
import com.example.appocr.database.SQLiteHelper

class ManageActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val db by lazy { SQLiteHelper(this) }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)

        recyclerView = findViewById(R.id.rcv_product)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val productList = loadProductList()
        productAdapter = ProductAdapter(productList = productList)
        recyclerView.adapter = productAdapter

        val addProductButton: LinearLayout = findViewById(R.id.button_addProdut)
        addProductButton.setOnClickListener {
            navigateToAddProductActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        // Reload the product list when the activity is resumed
        val productList = loadProductList()
        productAdapter.updateProductList(productList)
    }

    private fun navigateToAddProductActivity() {
        val intent = Intent(this, AddProductActivity::class.java)
        startActivity(intent)
    }

    private fun loadProductList(): List<Inventory> {
        // Load the product list from the database or any other source
        return db.getAll()
    }
}
