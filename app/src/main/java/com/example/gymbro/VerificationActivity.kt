package com.example.gymbro

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gymbro.databinding.ActivityVerificationBinding
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class VerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)



        configureUI()
    }

    private fun configureUI() {


        binding.buttonVerificationDone.setOnClickListener() {

        }
    }






}