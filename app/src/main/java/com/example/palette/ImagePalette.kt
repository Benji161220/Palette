package com.example.palette

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.example.palette.databinding.ActivityImagePaletteBinding

class ImagePalette : AppCompatActivity() {

    private lateinit var binding: ActivityImagePaletteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePaletteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageResource = intent.getIntExtra("image_resource", 0)
        if (imageResource != 0) {
            binding.paletteImage.setImageResource(imageResource)
        }

        setupTransitions()

        val imageDrawable = binding.paletteImage.drawable
        if (imageDrawable is BitmapDrawable) {
            val bitmap = imageDrawable.bitmap
            createPalette(bitmap)
        }
    }

    private fun setupTransitions() {
        window.enterTransition = Fade()
        window.exitTransition = Slide(Gravity.TOP)

        val transition = TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform)
        window.sharedElementEnterTransition = transition
        window.sharedElementReturnTransition = transition
    }

    private fun createPalette(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            palette?.let {
                binding.lightVibrantSwatch.setBackgroundColor(it.getLightVibrantColor(0))
                binding.mutedSwatch.setBackgroundColor(it.getMutedColor(0))
                binding.darkMutedSwatch.setBackgroundColor(it.getDarkMutedColor(0))
                binding.lightMutedSwatch.setBackgroundColor(it.getLightMutedColor(0))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}