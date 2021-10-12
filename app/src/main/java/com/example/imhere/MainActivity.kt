package com.example.imhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val SPLASH_SCREEN = 3000

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    private lateinit var imageView: ImageView
    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        imageView = findViewById(R.id.hr_image)
        title = findViewById(R.id.title_text)

        imageView.animation = topAnimation
        title.animation = bottomAnimation

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())


    }
}