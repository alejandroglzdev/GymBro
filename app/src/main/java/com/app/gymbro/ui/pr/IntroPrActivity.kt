package com.app.gymbro.ui.pr

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import androidx.annotation.RequiresApi
import com.app.gymbro.databinding.ActivityIntroPrBinding
import com.app.gymbro.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.util.*

/**
 * This class represents the IntroPrActivity in the app.
 * It's main function is to handle the UI for adding new personal records to the user's Firebase Firestore database.
 * The class uses Firebase Firestore and Firebase Auth for adding new personal records and authenticating the user.
 *
 * @property binding: The data binding object for the activity's layout
 * @property database: A FirebaseFirestore instance for accessing the database
 * @property auth: A FirebaseAuth instance for authenticating the user
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
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityIntroPrBinding.inflate(layoutInflater)
            setContentView(binding.root)

            initViews()
        }

        /**
         * Initializes the views for the activity by setting onClickListeners for the back and send button.
         */
        @RequiresApi(Build.VERSION_CODES.N)
        private fun initViews() {

            var selectedDate: Calendar = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                binding.dateEditTextSignIn.setText(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time))
            }

            binding.backImageView.setOnClickListener{ goBackPr() }
            binding.sendButtonPR.setOnClickListener{ addNewPrData() }
            binding.dateEditTextSignIn.setOnClickListener{
                binding.dateEditTextSignIn.requestFocus()
                DatePickerDialog(this, dateSetListener,
                selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)).show()
            }


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
            setShowReviewToFalse()
            goBackPr()
        }

        /**
         * Navigates the user back to the MainActivity.
         */
        private fun goBackPr() {
            onBackPressed() //TODO: Deprecated
        }

        /**
        * It creates a boolean in the SharedPreferences
        */
        private fun setShowReviewToFalse() {
            val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putBoolean("showReviewCard",false)
            editor.commit()
        }

    }