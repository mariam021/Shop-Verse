package com.example.shopverse.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shopverse.R
import com.example.shopverse.databinding.ActivityEnteryBinding
import com.example.shopverse.databinding.ActivityMainBinding

class EnteryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnteryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnteryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val currentFragmentId = navController.currentDestination?.id

// Check if the current fragment is the ProfileFragment
        if (currentFragmentId == R.id.profileFragment) {
            // The ProfileFragment is the current fragment

            val goToLogin = intent.getBooleanExtra("goToLogin", false)
            if (goToLogin) {
                // Navigate directly to the LoginFragment
                navController.navigate(R.id.loginFragment)
            } else {
                // Show the WelcomeFragment first
                navController.navigate(R.id.splashFragment)
            }

        }
    }
}