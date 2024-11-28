package com.example.shopverse.presentation.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignUpFragmentVMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpFragmentVM::class.java)) {
            return SignUpFragmentVM(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}