package com.example.gymbro

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gymbro.databinding.ActivitySignUpBinding
import com.example.gymbro.ui.register.VerifyActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    val firebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
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

                        //agregamos los users al firestore
                        val userFirestore = hashMapOf(
                            "username" to username,
                            "phone" to phone,
                            "email" to email,
                            "name" to null,
                            "surnames" to null,
                            "photoURL" to null,
                            "description" to null,
                            "birthDate" to null,
                            "gender" to null
                        )

                        db.collection("users")
                            .document(firebaseAuth.currentUser!!.uid)
                            .set(userFirestore)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }


                        //redirigimos al usuario a la vista de verificacion
                        updateUserInfoAndGoSignIn(email, password1)
                    } else {
                        //si el registro falla entra aqui
                        Toast.makeText(this, "Error durante el registro", Toast.LENGTH_SHORT).show()
                    }


                }


        } else {
            Toast.makeText(this, "Fill all the gaps", Toast.LENGTH_SHORT).show()
        }


    }

    private fun updateUserInfoAndGoSignIn(email: String, password1: String) {
        sendEmailVerification()
        //vamos a la actividad entercodeactivity
        val intent = Intent(this, VerifyActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("password", password1)
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