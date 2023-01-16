package com.app.gymbro.ui.register

import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.app.gymbro.R
import com.app.gymbro.SignInActivity
import com.app.gymbro.databinding.ActivityVerifyBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.schedule

class VerifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyBinding

    val firebaseAuth = FirebaseAuth.getInstance()

    val firebaseUser = firebaseAuth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val password = bundle?.getString("password")

        binding.buttonCheck.setOnClickListener {
            checkIfVerified(email, password)
        }

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.textViewResend.setOnClickListener {
            Toast.makeText(this, "Mail sent, check your spam inbox", Toast.LENGTH_SHORT).show()
        }



    }


    private fun checkIfVerified(email: String?, password: String?) {

            if (email != null) {
                if (password != null) {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(
                            email,
                            password
                        )
                        .addOnCompleteListener {

                            if (it.isSuccessful) {
                                if (firebaseUser?.isEmailVerified == true) {

                                    binding.animationViewLoading.setAnimation(R.raw.loadingdone)
                                    binding.animationViewLoading.playAnimation()
                                    binding.animationViewLoading.loop(false)
                                    binding.buttonCheck.isVisible = false
                                    binding.buttonNext.isVisible = true

                                } else {
                                    Toast.makeText(this, "Mail isn't verified, check your inbox", Toast.LENGTH_SHORT)
                                        .show()
                                    binding.buttonCheck.isActivated = true
                                }

                            } else {
                                //si el login falla entra aqui
                                Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
                                binding.buttonCheck.isActivated = true
                            }
                        }
                }
            }
    }
}