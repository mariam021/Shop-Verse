package com.example.shopverse.presentation.signup

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.user.User
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.launch

class SignUpFragmentVM(context: Context) : ViewModel() {
    private val userRepository = UserRepository(UserDatabase.getUserDatabase(context).userDao())
    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> get() = _signupResult

    fun signUp(username: String, email: String, password: String, phone: String) {
        viewModelScope.launch {
            try {
                val user = User(email = email, password = password, userName = username, phone = phone)
                val isInserted = userRepository.insertUserIfNotExists(user)
                Log.d("SignUpFragment", "Inserted User ID: ${user?.id}")
                _signupResult.value = isInserted
            } catch (e: Exception) {
                e.printStackTrace()
                _signupResult.value = false
            }
        }
    }
}
