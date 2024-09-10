package com.example.shopverse.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getUser(): User

    @Query("SELECT * FROM User WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE isLoggedIn = 1 LIMIT 1")
    suspend fun getLoggedInUser(): User?
    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE User SET isLoggedIn = :isLoggedIn WHERE email = :email")
    suspend fun setUserLoggedInStatus(email: String, isLoggedIn: Boolean)
}
