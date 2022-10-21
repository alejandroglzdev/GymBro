package com.example.gymbro.ui.resetPassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gymbro.databinding.ActivityCreateNewPasswordBinding
import com.example.gymbro.ui.login.SignInActivity

class CreateNewPasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateNewPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()

    }

    private fun configureUI() {
        binding.savePasswordButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        binding.arrowBackImageView.setOnClickListener {
            val intent = Intent(this, EnterCodeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}