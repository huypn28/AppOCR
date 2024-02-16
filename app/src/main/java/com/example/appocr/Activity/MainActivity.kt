package com.example.appocr.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.example.appocr.R

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manageButton: LinearLayout = findViewById(R.id.button_manage)
        manageButton.setOnClickListener {
            navigateToManageActivity()
        }

    }

    private fun navigateToManageActivity() {
        val intent = Intent(this, ManageActivity::class.java)
        startActivity(intent)
    }
}