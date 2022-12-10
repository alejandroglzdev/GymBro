package com.example.gymbro.ui.pr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymbro.R
import com.example.gymbro.databinding.ActivityIntroPrBinding
import com.example.gymbro.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class IntroPrActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroPrBinding
    private val database = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroPrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.backImageView.setOnClickListener{ goBackPr() }
        binding.sendButtonPR.setOnClickListener{ addNewPrData() }
    }

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

    private fun goBackPr() {
        onBackPressed() //TODO: Deprecated
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }


}