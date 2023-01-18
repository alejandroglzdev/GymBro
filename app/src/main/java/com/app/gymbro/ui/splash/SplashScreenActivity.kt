package com.app.gymbro.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.gymbro.R
import com.app.gymbro.SignInActivity

/**
 * SplashScreenActivity is a class that shows the splash screen when the app is launched.
 * It displays an image for 1 second before redirecting the user to the SignInActivity.
 *
 * @author GymBro Team
 */
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}