package com.example.appocr.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appocr.Model.Inventory
import com.example.appocr.R
import com.example.appocr.database.SQLiteHelper

class AddProductActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product_activity)
        val db = SQLiteHelper(this)
        db.addInventory(Inventory("Iphone14",10,"10000000000"))
    }

}