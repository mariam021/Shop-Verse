package com.example.shopverse.presentation.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.launch

class LoginFragmentVM(context: Context) : ViewModel() {
    private val userRepository = UserRepository(UserDatabase.getUserDatabase(context).userDao())
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmail(email)
            _loginResult.value = user?.let { it.password == password } ?: false
        }
    }
}
