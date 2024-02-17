package com.example.appocr.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appocr.Model.Inventory
import com.example.appocr.R
import com.example.appocr.database.SQLiteHelper

class AddProductActivity : AppCompatActivity() {

    private lateinit var productNameEditText: EditText
    private lateinit var productPriceEditText: EditText
    private lateinit var productQuantityEditText: EditText
    private lateinit var addProductButton: TextView
    private val db by lazy { SQLiteHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product_activity)

        productNameEditText = findViewById(R.id.edt_add_ten)
        productPriceEditText = findViewById(R.id.edt_add_lop)
        productQuantityEditText = findViewById(R.id.edt_add_msv)
        addProductButton = findViewById(R.id.tv_add_sv)

        addProductButton.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        val productName = productNameEditText.text.toString()
        val productPrice = productPriceEditText.text.toString()
        val productQuantity = productQuantityEditText.text.toString().toIntOrNull()

        if (productName.isBlank() || productPrice.isBlank() || productQuantity == null) {
            // Display an error message for incomplete input
            showToast("Please fill in all fields")
            return
        }

        val product = Inventory(productName, productQuantity, productPrice)

        // Add the product to the database
        val result = db.addInventory(product)

        if (result != -1L) {
            // Show success message and return to ManagerActivity
            showToast("Product added successfully")
            navigateToManagerActivity()
        } else {
            // Show an error message if adding to the database failed
            showToast("Failed to add product")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToManagerActivity() {
        val intent = Intent(this, ManageActivity::class.java)
        startActivity(intent)
        finish() // Optional: Finish the current activity to remove it from the back stack
    }


}
