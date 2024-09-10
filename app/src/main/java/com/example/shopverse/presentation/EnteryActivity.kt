package com.example.shopverse.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopverse.R
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.ActivityEnteryBinding
import com.example.shopverse.databinding.ActivityMainBinding
import com.example.shopverse.presentation.NavigationDestination.*
import com.example.shopverse.presentation.main.MainActivity
import com.example.shopverse.presentation.splash.SplashFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class EnteryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnteryBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnteryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        lifecycleScope.launch {
            var destination = intent.getSerializableExtra("navigationSource") as? NavigationDestination
                ?: OTHER

            delay(1200)

            navigateBasedOnSource(destination)
        }
    }

    // Function to check if a user exists in the database
    private suspend fun checkIfUserExists(): Boolean {
        return withContext(Dispatchers.IO) {
            val userDao = UserDatabase.getUserDatabase(this@EnteryActivity).userDao()
            val user = userDao.getLoggedInUser()
            user != null
        }
    }

    // Function to handle navigation based on the destination
    private suspend fun navigateBasedOnSource(destination: NavigationDestination) {
        when (destination) {
            ProfileFragment -> {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                navController.navigate(action)
            }
            WelcomeFragment -> {

            }
            LoginFragment -> {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                navController.navigate(action)
            }
            HomeFragment -> {
            }
            OTHER -> {
                val userExists = checkIfUserExists()
                if (userExists) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val action = SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
                    navController.navigate(action)
                }
            }
            FavouriteFragment -> {
                // TODO: Handle navigation to FavouriteFragment when needed
            }

            OTHER -> TODO()
        }
    }
}
