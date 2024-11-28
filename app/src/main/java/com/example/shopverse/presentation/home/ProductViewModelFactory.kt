package com.example.shopverse.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.domain.repo.user.UserRepository


class ProductViewModelFactory(
    private val productRepository: ProductRepository,
    private val  userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            return HomeVM(productRepository,userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}