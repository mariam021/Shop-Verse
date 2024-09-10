package com.example.shopverse.presentation.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.domain.repo.user.UserRepository

class EntryVMFactory(private val repository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryVM::class.java)) {
            return EntryVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}