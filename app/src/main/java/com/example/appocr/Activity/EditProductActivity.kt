package com.example.appocr.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appocr.Model.Inventory
import com.example.appocr.R
import com.example.appocr.database.SQLiteHelper
import com.example.appocr.Activity.ManageActivity

class EditProductActivity : AppCompatActivity() {

    private lateinit var productNameEditText: EditText
    private lateinit var productPriceEditText: EditText
    private lateinit var productQuantityEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var cancelButton: Button
    private lateinit var db: SQLiteHelper

    private var productId: Int = 0 // Initialize with a default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_product_activity)

        db = SQLiteHelper(this)

        productNameEditText = findViewById(R.id.edt_ten)
        productPriceEditText = findViewById(R.id.edt_gia)
        productQuantityEditText = findViewById(R.id.edt_index)
        updateButton = findViewById(R.id.bt_update)
        cancelButton = findViewById(R.id.bt_cancel)

        // Retrieve product details from the Intent
        productId = intent.getIntExtra("productId", 0)
        val productName = intent.getStringExtra("productName") ?: ""
        val productQuantity = intent.getIntExtra("productQuantity", 0)
        val productPrice = intent.getStringExtra("productPrice") ?: ""

        // Populate EditText fields with existing product details
        productNameEditText.setText(productName)
        productQuantityEditText.setText(productQuantity.toString())
        productPriceEditText.setText(productPrice)

        updateButton.setOnClickListener {
            updateProduct(productId)
        }

        cancelButton.setOnClickListener {
            // If "Cancel" is clicked, go back to ManageActivity
            navigateToManageActivity()
        }
    }

    private fun updateProduct(productId: Int) {
        // Retrieve updated product details from EditText fields
        val updatedProductName = productNameEditText.text.toString()
        val updatedProductPrice = productPriceEditText.text.toString()
        val updatedProductQuantity = productQuantityEditText.text.toString().toIntOrNull() ?: 0

        // Create an Inventory object with the updated details
        val updatedProduct = Inventory(productId, updatedProductName, updatedProductQuantity, updatedProductPrice)

        // Update the product in the database
        val rowsAffected = db.update(updatedProduct)

        if (rowsAffected > 0) {
            // Display a success message and go back to ManageActivity
            showToast("Product updated successfully")
            navigateToManageActivity()
        } else {
            // Display an error message
            showToast("Failed to update product")
        }
    }

    private fun navigateToManageActivity() {
        val intent = Intent(this, ManageActivity::class.java)
        startActivity(intent)
        finish() // Optional: Finish the current activity to remove it from the back stack
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
