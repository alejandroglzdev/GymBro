package com.example.gymbro.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import com.example.gymbro.R
import com.example.gymbro.databinding.ActivityMainBinding
import com.example.gymbro.ui.feed.fragment.FeedFragment
import com.example.gymbro.ui.search.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, FeedFragment.newInstance("", "")).commit()

        Log.d(TAG, "MainActivity:onCreate: ")

        configureUI()
    }

    private fun configureUI() {
        binding.homeImageView.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, FeedFragment.newInstance("", "")).commit()

            Log.d(TAG, "MainActivity:onCreate: ")
        }

        binding.dietImageView.setOnClickListener { }

        binding.searchImageView.setOnClickListener {


            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, SearchFragment.newInstance("", "")).commit()

                Log.d(TAG, "MainActivity:onCreate: ")


            /*
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

             */
        }

        binding.trainingImageView.setOnClickListener { }

        binding.profileImageView.setOnClickListener { }

    }
}