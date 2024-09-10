package com.example.shopverse.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.presentation.home.HomeVM


class SearchVMFactory(
    private val productRepository: ProductRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchVM::class.java)) {
            return SearchVM(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}