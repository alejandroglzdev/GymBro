package com.example.gymbro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymbro.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

    }

    private fun setup() {

        binding.nextButton.setOnClickListener() {
            if (binding.usernameEditText.text.isNullOrEmpty()) {
                binding.usernameEditText.error = "Username cannot be empty"
            }
            if (binding.emailEditText.text.isNullOrEmpty()) {
                binding.emailEditText.error = "Email cannot be empty"
            }
            if (binding.phoneEditText.text.isNullOrEmpty()) {
                binding.phoneEditText.error = "Phone cannot be empty"
            }


        }

    }
}