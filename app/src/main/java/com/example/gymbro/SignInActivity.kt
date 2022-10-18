package com.example.gymbro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymbro.databinding.ActivitySignInBinding
import com.example.gymbro.databinding.ActivitySignUpBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    private fun configureUI() {
        binding.forgotPasswordButtonSignIn.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.loginButtonSignIn.setOnClickListener {
            val intent = Intent(this,FeedActivity::class.java)
            startActivity(intent)
        }

        binding.signUpTextButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}