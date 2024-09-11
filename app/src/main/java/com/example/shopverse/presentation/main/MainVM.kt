package com.example.shopverse.presentation.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.shopverse.R

class MainVM : ViewModel() {
    private var _navController: NavController? = null

    fun setNavController(navController: NavController) {
        _navController = navController
    }

    fun navigateToHome() {
        _navController?.navigate(R.id.homeFragment)
    }

    fun navigateToSearch() {
        _navController?.navigate(R.id.searchFragment)
    }

    fun navigateToFavorites() {
        _navController?.navigate(R.id.favoriteFragment)
    }

    fun navigateToProfile() {
        _navController?.navigate(R.id.profileFragment)
    }
}
