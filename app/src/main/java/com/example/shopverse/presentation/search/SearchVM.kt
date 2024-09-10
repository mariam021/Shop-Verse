package com.example.shopverse.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.product.Product
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.launch

class SearchVM(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun fetchProducts() {
        viewModelScope.launch {
            productRepository.fetchAndSaveProducts()
            _products.value = productRepository.getAllProducts()
        }
    }

}