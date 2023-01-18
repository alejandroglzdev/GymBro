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

/**
 * VerifyActivity is the class that handles the email verification process for new users.
 * It allows the user to input the verification code sent to their email.
 * Once the user has entered the correct code, the class verifies the email and redirects the user to SignInActivity.
 *
 * @author Gymbro Team
*/
class VerifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyBinding

    val firebaseAuth = FirebaseAuth.getInstance()

    val firebaseUser = firebaseAuth.currentUser

    /**
     * onCreate method is called when the activity is first created.
     * It sets the layout for the activity and initializes the UI elements.
     *
     * @param savedInstanceState Bundle containing the data it most recently supplied in
     */
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

    /**
     * checkIfVerified method is used to check if the email is verified or not.
     * It takes in email and password as input and uses them to sign in the user.
     * If the sign in is successful, it checks if the email is verified or not.
     * If the email is verified, it plays a loading animation and redirects the user to SignInActivity.
     * If the email is not verified, it displays a message asking the user to check their inbox.
     *
     * @param email the email of the user
     * @param password the password of the user
     */
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