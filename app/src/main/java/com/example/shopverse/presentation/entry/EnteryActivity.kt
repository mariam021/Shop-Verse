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
import com.example.shopverse.presentation.entry.NavigationDestination.*
import com.example.shopverse.presentation.main.MainActivity
import com.example.shopverse.presentation.splash.SplashFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        val userRepository = UserRepository(UserDatabase.getUserDatabase(this).userDao())
        entryVM = ViewModelProvider(this, EntryVMFactory(userRepository))[EntryVM::class.java]

        lifecycleScope.launch {
            val destination = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("navigationSource", NavigationDestination::class.java) ?: SplashFragment
            } else {
                @Suppress("DEPRECATION")
                intent.getSerializableExtra("navigationSource") as? NavigationDestination ?: SplashFragment
            }

            delay(1200)

            navigateBasedOnSource(destination)
        }
    }

    private suspend fun navigateBasedOnSource(destination: NavigationDestination) {
        val user = entryVM.getUserWithLoginStatus()
        when (destination) {
            ProfileFragment -> {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                navController.navigate(action)
            }
            SplashFragment -> {
                if (user != null) {
                    if (user.isLoggedIn) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                        navController.navigate(action)
                    }
                } else {
                    val action = SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
                    navController.navigate(action)
                }
            }
            else -> {
            }
        }
    }
}
