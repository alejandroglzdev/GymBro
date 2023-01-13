package com.app.gymbro.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.app.gymbro.R
import com.app.gymbro.databinding.ActivityMainBinding
import com.app.gymbro.ui.feed.fragment.FeedFragment
import com.app.gymbro.ui.profile.ProfileFragment

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
            navController.navigate(R.id.action_global_feedFragment)

        }

        binding.PrImageView.setOnClickListener{
            navController.navigate(R.id.action_global_prFragment)
        }

        binding.profileImageView.setOnClickListener {
            navController.navigate(R.id.action_global_profileFragment)

        }

    }
}