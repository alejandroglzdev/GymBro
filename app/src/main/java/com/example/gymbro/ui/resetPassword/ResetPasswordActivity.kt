package com.example.gymbro.ui.resetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymbro.databinding.ActivityResetPasswordBinding
import com.example.gymbro.ui.login.SignInActivity

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    private fun configureUI() {
        binding.sendButton.setOnClickListener {
            val intent = Intent(this, EnterCodeActivity::class.java)
            startActivity(intent)
        }

        binding.arrowBackImageView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}