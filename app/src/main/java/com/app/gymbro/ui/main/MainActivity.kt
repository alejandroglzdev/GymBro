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

/**
 * MainActivity is the entry point of the app, it's the first activity that runs when the app is launched.
 * It sets up the navigation between fragments using a NavHostFragment and a NavController.
 * It also sets up the onClickListeners for the bottom navigation buttons to navigate to different fragments.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /**
     * onCreate is called when the activity is created, it inflates the layout and sets it as the content view.
     * It also sets up the NavController and navigates to the FeedFragment.
     * It also calls the configureUI method to set up the onClickListeners for the bottom navigation buttons.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state
     */
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

    /**
     * configureUI sets up the onClickListeners for the bottom navigation buttons.
     * When the home button is clicked it navigates to the FeedFragment.
     * When the PR button is clicked it navigates to the PRFragment.
     * When the profile button is clicked it navigates to the ProfileFragment.
     *
     * @param navController NavController to handle navigation between fragments.
     */

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