package com.example.shopverse.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.product.Product
import com.example.shopverse.domain.repo.product.ProductRepository
import kotlinx.coroutines.launch

class FavVM(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun getFavoriteProducts() {
        viewModelScope.launch {
            _products.value = repository.getFavoriteProducts()
        }
    }

    fun removeProductFromFavorites(product: Product) {
        viewModelScope.launch {
            repository.removeProductFromFavorites(product)
            // After removal, fetch the updated list and post it to the LiveData
            _products.value = repository.getFavoriteProducts()
        }
    }
}