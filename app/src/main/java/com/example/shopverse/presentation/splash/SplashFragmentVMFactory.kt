package com.example.shopverse.presentation.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SplashFragmentVMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashFragmentVM::class.java)) {
            return SplashFragmentVM(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}