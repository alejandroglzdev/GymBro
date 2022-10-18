package com.example.gymbro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymbro.databinding.ActivityEnterCodeBinding

class EnterCodeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEnterCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    private fun configureUI() {
        binding.verifyButton.setOnClickListener {
            val intent = Intent(this, CreateNewPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.arrowBackImageView.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}