package com.example.shopverse.presentation.itemDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.product.Product
import com.example.shopverse.domain.repo.product.ProductRepository
import kotlinx.coroutines.launch

class ItemDetailsVM(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun addProductToFavorites(product: Product) {
        viewModelScope.launch {
            repository.addProductToFavorites(product)
        }
    }

    fun removeProductFromFavorites(product: Product) {
        viewModelScope.launch {
            repository.removeProductFromFavorites(product)
        }
    }
}