package com.app.gymbro.ui.pr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gymbro.databinding.ActivityIntroPrBinding
import com.app.gymbro.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

    /**
    This class represents the IntroPrActivity in the app.
    It's main function is to handle the UI for adding new personal records to the user's Firebase Firestore database.
    The class uses Firebase Firestore and Firebase Auth for adding new personal records and authenticating the user.
    @property binding: The data binding object for the activity's layout
    @property database: A FirebaseFirestore instance for accessing the database
    @property auth: A FirebaseAuth instance for authenticating the user
    */

class IntroPrActivity : AppCompatActivity() {

        private lateinit var binding: ActivityIntroPrBinding
        private val database = FirebaseFirestore.getInstance()
        private lateinit var auth: FirebaseAuth
        val firebaseAuth = FirebaseAuth.getInstance()

        /**
         * Called when the activity is starting. Initializes the data binding and sets the content view.
         * Also calls the initViews() method to set up the buttons' onClickListeners.
         *
         * @param savedInstanceState: If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
         */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityIntroPrBinding.inflate(layoutInflater)
            setContentView(binding.root)

            initViews()
        }

        /**
         * Initializes the views for the activity by setting onClickListeners for the back and send button.
         */
        private fun initViews() {
            binding.backImageView.setOnClickListener{ goBackPr() }
            binding.sendButtonPR.setOnClickListener{ addNewPrData() }
        }

        /**
         * Adds new personal record data to the user's Firebase Firestore database.
         * The data includes the exercise, date, weight, and repititions.
         */
        private fun addNewPrData() {
            auth = FirebaseAuth.getInstance()
            val user: FirebaseUser = auth.currentUser!!
            database.collection("users")
                .document(firebaseAuth.currentUser!!.uid)
                .collection("personal_record")
                .document()
                .set(
                    hashMapOf(
                        "ejercicio" to binding.exerciseEditTextSignIn.text.toString(),
                        "fecha" to binding.dateEditTextSignIn.text.toString(),
                        "peso" to binding.weightEditTextSignIn.text.toString(),
                        "repeticiones" to binding.repEditTextSignIn.text.toString()
                    )
                )
            goBackPr()
        }

        /**
         * Navigates the user back to the MainActivity.
         */
        private fun goBackPr() {
            onBackPressed() //TODO: Deprecated
        }

    }