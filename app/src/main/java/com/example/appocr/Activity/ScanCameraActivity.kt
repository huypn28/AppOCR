package com.example.appocr.Activity

import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import com.example.appocr.R


class CameraActivity : AppCompatActivity() , SurfaceHolder.Callback{
    private var previewView: PreviewView? = null
    private var camera: Camera? = null
    private var surfaceView: SurfaceView? = null
    private lateinit var surfaceHolder: SurfaceHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_camera_activity)
        surfaceView = findViewById<SurfaceView>(R.id.cameraPreview)
        if (surfaceView!= null) {
            surfaceHolder = surfaceView!!.holder
            surfaceHolder.addCallback(this)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        camera = Camera.open()
        val parameters: Camera.Parameters = camera?.parameters!!
        camera?.setParameters(parameters)
        try {
            camera?.setPreviewDisplay(holder)
            camera?.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error starting camera preview", Toast.LENGTH_SHORT).show()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (surfaceHolder!!.surface == null) {
            return
        }
        try {
            camera?.stopPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            camera?.setPreviewDisplay(surfaceHolder)
            camera?.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error starting camera preview", Toast.LENGTH_SHORT).show()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        camera?.stopPreview()
        camera?.release()
        camera = null
    }
}