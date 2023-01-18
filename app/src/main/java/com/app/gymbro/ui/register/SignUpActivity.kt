package com.app.gymbro

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.gymbro.databinding.ActivitySignUpBinding
import com.app.gymbro.ui.register.VerifyActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * SignUpActivity is the class that handles the user registration process.
 * It allows the user to input their information such as username, phone number, email and password.
 * Once the user has entered all the required information, the class creates a new user account
 * using FirebaseAuth and stores the additional information in the Firebase database under the user's unique ID.
 * Also creates the user on a firestore database with additional fields such as name, surnames, birthdate and gender.
 * The user will be redirected to SignInActivity when they press the arrowBackImageView or signInTextButton.
 *
 * @author Gymbro Team
 */
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    val firebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val firebaseUser = firebaseAuth.currentUser

    /**
     * onCreate method is called when the activity is first created.
     * It sets the layout for the activity and initializes the UI elements.
     *
     * @param savedInstanceState Bundle containing the data it most recently supplied in
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureUI()
    }

    /**
     * configureUI method sets up the UI elements such as buttons and EditText fields
     * for the user to input their information.
     */
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


        binding.nextButton.setOnClickListener {
            if (binding.passwd1EditText.text.toString() != binding.passwd2EditText.text.toString()) {
                binding.passwd1EditText.setError("Password does not match")
            } else {
                signUp()
            }

        }
    }

    /**
     * signUp method creates a new user account with the provided email and password, and stores
     * the additional information (username, phone number) in the Firebase database under the user's unique ID.
     * Also creates the user on a firestore database with additional fields such as name, surnames, birthdate and gender.
     */
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
                        database.child("users").child(user.uid).child("username")
                            .setValue(username)
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
                                Log.d(
                                    TAG,
                                    "DocumentSnapshot added with ID: ${documentReference}"
                                )
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }


                        //redirigimos al usuario a la vista de verificacion
                        updateUserInfoAndGoSignIn(email, password1)
                    } else {
                        //si el registro falla entra aqui
                        Toast.makeText(this, "Error durante el registro", Toast.LENGTH_SHORT)
                            .show()
                    }


                }
        } else {
            binding.passwd1EditText.setError("Passwords does not match")
        }
        
    }

    /**
     * updateUserInfoAndGoSignIn method sends a email verification to the user and redirects the user to VerifyActivity
     * @param email the email of the user
     * @param password1 the password of the user
     */
    private fun updateUserInfoAndGoSignIn(email: String, password1: String) {
        sendEmailVerification()
        //vamos a la actividad entercodeactivity
        val intent = Intent(this, VerifyActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("password", password1)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    /**
     * sendEmailVerification method sends a email verification to the user
     *
     */
    private fun sendEmailVerification() {
        auth.currentUser!!.sendEmailVerification()
            .addOnSuccessListener {
                Toast.makeText(this, "Email sent...", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to send due to " + e.message, Toast.LENGTH_SHORT)
                    .show()
            }

    }


}