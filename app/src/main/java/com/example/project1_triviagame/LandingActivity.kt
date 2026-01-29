package com.example.project1_triviagame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.DecelerateInterpolator

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val tapText = findViewById<View>(R.id.tapText)

        // delay 1 second - https://android.googlesource.com/platform/frameworks/support/+/5d1be99/v4/java/android/support/v4/app/FragmentManager.java
        Handler(Looper.getMainLooper()).postDelayed({
            tapText.animate()
                .alpha(1f)
                .setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }, 1000)


        findViewById<View>(android.R.id.content).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
