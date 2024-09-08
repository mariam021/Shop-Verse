package com.example.shopverse.presentation.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainVM: ViewModel() {
    private var _navController: NavController? = null

    fun setNavController(navController: NavController) {
        _navController = navController
    }

}