package com.example.shopverse.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String,
    val userName: String,
    val phone: String,
    val isLoggedIn: Boolean = false
)
