package com.example.shopverse.presentation.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.user.User
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.launch

class ProfileVM(context: Context) : ViewModel() {

    private val userRepository = UserRepository(UserDatabase.getUserDatabase(context).userDao())
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun loadUser() {
        viewModelScope.launch {
            val fetchedUser = userRepository.getUser()
            _user.value = fetchedUser
        }
    }

//    fun logoutUser() {
//        viewModelScope.launch {
//            _user.value?.let { user ->
//                userRepository.setUserLoggedInStatus(user.email, false)
//            }
//        }
//    }
}
