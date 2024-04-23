package com.example.studysync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import auth.Sign_in

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //  fetch animation and image on which animation should be reflacted
        val animation: Animation = AnimationUtils.loadAnimation(baseContext, R.anim.blinking_animation)
        val logo: ImageView= findViewById<ImageView>(R.id.logo)
        //start animation
        logo.startAnimation(animation)
        // pause splash screen for 1.5 sec then redirect to main activity
        Handler().postDelayed({
                startActivity(Intent(this, Sign_in()::class.java))
                finish()
            },1500)
    }
}