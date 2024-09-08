package com.example.shopverse.presentation.itemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.domain.repo.product.ProductRepository

class ItemDetailsViewModelFactory( private val repository: ProductRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailsVM::class.java)) {
            return ItemDetailsVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}