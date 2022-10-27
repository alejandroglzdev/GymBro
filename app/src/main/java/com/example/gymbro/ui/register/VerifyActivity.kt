package com.example.gymbro.ui.register

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gymbro.R
import com.example.gymbro.databinding.ActivityVerifyBinding
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

        checkIfVerified(email, password)


    }


    private fun checkIfVerified(email: String?, password: String?) {
        var verified = false
        while (!verified)

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
                                    verified = true

                                } else {
                                    verified = false
                                    Toast.makeText(this, "Mail isn't verified", Toast.LENGTH_SHORT)
                                        .show()
                                }

                            } else {
                                //si el login falla entra aqui
                                Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
    }
}