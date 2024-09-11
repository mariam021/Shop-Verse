package com.example.shopverse.domain.repo.user

import com.example.shopverse.data.local.user.User
import com.example.shopverse.data.local.user.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun getUser(): User {
        return userDao.getUser()
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun insertUserIfNotExists(user: User): Boolean {
        val existingUser = userDao.getUserByEmail(user.email)
        return if (existingUser == null) {
            userDao.insertUser(user)
            true
        } else {
            false
        }
    }

    suspend fun getLoggedInUser(): User? {
        return userDao.getLoggedInUser()
    }

    suspend fun setUserLoggedInStatus(email: String, isLoggedIn: Boolean) {
        userDao.setUserLoggedInStatus(email, isLoggedIn)
    }
}