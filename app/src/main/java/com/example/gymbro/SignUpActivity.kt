package com.example.gymbro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gymbro.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    val firebaseAuth = FirebaseAuth.getInstance()

    val firebaseUser = firebaseAuth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }


    private fun configureUI() {
        binding.arrowBackImageView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        binding.signInTextButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()


        binding.nextButton.setOnClickListener() {
            signUp()
        }
    }

    private fun signUp() {

        var username = binding.usernameEditText.text.toString()
        var phone = binding.phoneEditText.text.toString()
        var email = binding.emailEditText.text.toString()
        var password1 = binding.passwd1EditText.text.toString()
        var password2 = binding.passwd2EditText.text.toString()

        if (username.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()
            && password1.isNotEmpty() && password2.isNotEmpty()
        ) {

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    email,
                    password1
                )
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        Toast.makeText(this, "Register complete", Toast.LENGTH_SHORT).show()

                        val user: FirebaseUser = auth.currentUser!!


                        //agregamos la demás info dentro de la base de datos del usuario con userid
                        database.child("users").child(user.uid).child("username").setValue(username)
                        database.child("users").child(user.uid).child("phone").setValue(phone)
                        database.child("users").child(user.uid).child("verification")
                            .setValue("false")

                        //redirigimos al usuario a la vista de verificacion
                        updateUserInfoAndGoSignIn()
                    } else {
                        //si el registro falla entra aqui
                        Toast.makeText(this, "Error durante el registro", Toast.LENGTH_SHORT).show()
                    }


                }


        } else {
            Toast.makeText(this, "Fill all the gaps", Toast.LENGTH_SHORT).show()
        }


    }

    private fun updateUserInfoAndGoSignIn() {
        sendEmailVerification()
        //vamos a la actividad entercodeactivity
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun sendEmailVerification() {


        firebaseUser!!.sendEmailVerification()
            .addOnSuccessListener {
                Toast.makeText(this, "Email sent...", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to send due to " + e.message, Toast.LENGTH_SHORT)
                    .show()
            }

    }


}