package com.example.gymbro

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.widget.Toast
import com.example.gymbro.databinding.ActivitySignInBinding
import com.example.gymbro.databinding.ActivitySignUpBinding
import com.example.gymbro.ui.main.MainActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.logging.Level.INFO

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    val firebaseAuth = FirebaseAuth.getInstance()

    val firebaseUser = firebaseAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    override fun onStart() {
        super.onStart()
        /*
        firebaseAuth is persistent, the only thing we have to do to keep the session
        logged is to check when SignInActivity start if you've already logged and
        if you have your email verified
         */
        val user = firebaseAuth.currentUser
        val intent = Intent(this, MainActivity::class.java)
        if (user != null){
            if (user.isEmailVerified){
                startActivity(intent)
            }
        }
    }

    private fun configureUI() {
        binding.forgotPasswordButtonSignIn.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.loginButtonSignIn.setOnClickListener {
            logInUser()
            /* val intent = Intent(this, MainActivity::class.java)
            UnComment to bypass login function
            startActivity(intent) */
        }

        binding.signUpTextButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun logInUser() {

        if (binding.usernameEditTextSignIn.text.toString()
                .isNotEmpty() && binding.passwordEditTextSignIn.text.toString().isNotEmpty()
        ) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(
                    binding.usernameEditTextSignIn.text.toString(),
                    binding.passwordEditTextSignIn.text.toString()
                )
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        if (firebaseUser?.isEmailVerified == true) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Mail isn't verified", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        //si el login falla entra aqui
                        Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
                    }


                }
        }


    }
}