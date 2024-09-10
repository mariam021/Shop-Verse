package com.example.shopverse.presentation.entry

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.shopverse.R
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.ActivityEnteryBinding
import com.example.shopverse.domain.repo.user.UserRepository
import com.example.shopverse.presentation.NavigationDestination
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
    private lateinit var entryVM: EntryVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnteryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Initialize ViewModel
        val userRepository = UserRepository(UserDatabase.getUserDatabase(this).userDao())
        entryVM = ViewModelProvider(this, EntryVMFactory(userRepository))[EntryVM::class.java]

        lifecycleScope.launch {
            val destination = intent.getSerializableExtra("navigationSource") as? NavigationDestination
                ?: OTHER

            delay(1200)

            navigateBasedOnSource(destination)
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
                // Handle navigation to WelcomeFragment
            }
            LoginFragment -> {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                navController.navigate(action)
            }
            HomeFragment -> {
                // Handle navigation to HomeFragment
            }
            OTHER -> {
                val user = entryVM.getUserWithLoginStatus() // Use ViewModel to get user and login status
                if (user != null) {
                    if (user.isLoggedIn) {
                        // Navigate to MainActivity if the user is logged in
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Navigate to LoginFragment if the user exists but is not logged in
                        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                        navController.navigate(action)
                    }
                } else {
                    // If no user exists, navigate to WelcomeFragment
                    val action = SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
                    navController.navigate(action)
                }
            }
            FavouriteFragment -> {
                // TODO: Handle navigation to FavouriteFragment when needed
            }

            else -> {
                // Default or other cases if necessary
            }
        }
    }
}
