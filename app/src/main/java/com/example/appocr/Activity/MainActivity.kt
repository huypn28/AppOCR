package com.example.appocr.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.appocr.R

class MainActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE=1
    val captureButton: Button=findViewById(R.id.button_capture)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captureButton.setOnClickListener{
            dispatchTakePictureIntent()
        }

    }

    private fun dispatchTakePictureIntent() {
        TODO("Not yet implemented")
    }
}