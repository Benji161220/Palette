package com.example.palette

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImagePalette: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtén la imagen seleccionada del Intent
        val selectedImage = getIntent().getIntExtra("image_resource", 0)

        // Configura la imagen en el ImageView
        val imageView = findViewById<ImageView?>(R.id.imageView2)
        imageView.setImageResource(selectedImage)
    }
}