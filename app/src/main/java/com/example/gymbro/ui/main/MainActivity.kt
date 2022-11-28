package com.example.gymbro.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gymbro.R
import com.example.gymbro.databinding.ActivityMainBinding
import com.example.gymbro.ui.feed.fragment.FeedFragment
import com.example.gymbro.ui.profile.ProfileFragment
import com.example.gymbro.ui.search.fragment.SearchFragment
import com.example.gymbro.ui.workoutMenu.fragment.WorkoutMenuFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_global_feedFragment)

        Log.d(TAG, "MainActivity:onCreate: ")

        configureUI(navController)
    }

    private fun configureUI(navController: NavController) {
        binding.homeImageView.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, FeedFragment()).commit()

            navController.navigate(R.id.nav_graph)
        }

        binding.dietImageView.setOnClickListener { }

        binding.searchImageView.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, SearchFragment.newInstance("", "")).commit()

                Log.d(TAG, "MainActivity:onCreate: ")

        }

        binding.trainingImageView.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, WorkoutMenuFragment.newInstance("", "")).commit()

            Log.d(TAG, "MainActivity:onCreate: ")
        }

        binding.profileImageView.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment.newInstance("", "")).commit()

            Log.d(TAG, "MainActivity:onCreate: ")
        }

    }
}