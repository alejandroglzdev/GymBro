package com.example.gymbro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gymbro.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding

    private lateinit var auth: FirebaseAuth

    val firebaseAuth = FirebaseAuth.getInstance()

    val firebaseUser = firebaseAuth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    private fun configureUI() {
        binding.sendButton.setOnClickListener {
            firebaseAuth.sendPasswordResetEmail(binding.usernameEditText.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "If exists, email sent to the account",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.arrowBackImageView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}