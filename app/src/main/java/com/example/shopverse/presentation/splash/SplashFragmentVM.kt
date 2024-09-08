package com.example.shopverse.presentation.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.launch

class SplashFragmentVM(context: Context) : ViewModel() {
    private val userRepository = UserRepository(UserDatabase.getUserDatabase(context).userDao())
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun checkLoginStatus() {
        viewModelScope.launch {
            val user = userRepository.getLoggedInUser() // Fetch the logged-in user
            _isLoggedIn.value = user != null // If user exists, set isLoggedIn to true, otherwise false
        }
    }
}