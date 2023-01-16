package com.app.gymbro

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.widget.Toast
import com.app.gymbro.databinding.ActivitySignInBinding
import com.app.gymbro.ui.main.MainActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.logging.Level.INFO

/**
 * SignInActivity is an AppCompatActivity that handles the sign in process for the user.
 * It allows the user to sign in with their email and password, and navigate to the MainActivity if the sign in is successful.
 * Additionally, it has a "forgot password" button that navigates to the ResetPasswordActivity and a "sign up" button that navigates to the SignUpActivity.
 */
class SignInActivity : AppCompatActivity() {
    /**
     * The binding variable for the activity layout.
     */
    private lateinit var binding: ActivitySignInBinding

    /**
     * The FirebaseAuth instance for handling authentication.
     */
    val firebaseAuth = FirebaseAuth.getInstance()

    /**
     * The current Firebase user.
     */
    val firebaseUser = firebaseAuth.currentUser

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     * Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    /**
     * Called when the activity is becoming visible to the user.
     */
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

    /**
     * Configures the UI elements of the activity.
     */
    private fun configureUI() {
        binding.forgotPasswordButtonSignIn.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.loginButtonSignIn.setOnClickListener {
            logInUser()
        }

        binding.signUpTextButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * Logs in the user with the provided email and password.
     */

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
                        //if (firebaseUser?.isEmailVerified == true) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                       // } else {
                          //  Toast.makeText(this, "Mail isn't verified", Toast.LENGTH_SHORT).show()
                       // }

                    } else {
                        //si el login falla entra aqui
                        Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
                    }


                }
        } else {
            binding.usernameEditTextSignIn.setError("Credentials must not be null")
        }


    }
}