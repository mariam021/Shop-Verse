package com.example.shopverse.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.domain.repo.product.ProductRepository


class FavViewModelFactory(private val repository: ProductRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavVM::class.java)) {
            return FavVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}