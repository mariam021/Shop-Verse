package com.example.shopverse.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginFragmentVMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginFragmentVM::class.java)) {
            return LoginFragmentVM(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}