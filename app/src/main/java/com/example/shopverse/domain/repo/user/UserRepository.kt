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

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun insertUserIfNotExists(user: User): Boolean {
        // Check if a user with the same email already exists
        val existingUser = userDao.getUserByEmail(user.email)
        return if (existingUser == null) {
            // If the user does not exist, insert the new user
            userDao.insertUser(user)
            true
        } else {
            // User already exists, return false or handle as needed
            false
        }
    }

    suspend fun getLoggedInUser(): User? {
        return userDao.getLoggedInUser() // Implement this in your DAO
    }
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}