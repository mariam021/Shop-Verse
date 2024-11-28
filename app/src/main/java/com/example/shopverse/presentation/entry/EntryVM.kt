package com.example.shopverse.presentation.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.user.User
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EntryVM(private val userRepository: UserRepository) : ViewModel() {
    private val _loggedInUser = MutableLiveData<User?>()
    val loggedInUser: LiveData<User?> get() = _loggedInUser

    suspend fun getUserWithLoginStatus(): User {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUser()
            _loggedInUser.postValue(user)
            user
        }
    }
}
