package com.example.shopverse.presentation.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopverse.data.local.product.Product
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.launch

class HomeVM(
    private val productRepository: ProductRepository, private val userRepository: UserRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _loggedInUserName = MutableLiveData<String?>()
    val loggedInUserName: LiveData<String?> get() = _loggedInUserName

    fun fetchLoggedInUserName() {
        viewModelScope.launch {
            val user = userRepository.getUser()
            _loggedInUserName.value = user.userName
        }
    }

        fun fetchProducts() {
            viewModelScope.launch {
                productRepository.fetchAndSaveProducts()
                _products.value = productRepository.getAllProducts()
            }
        }

        fun addProductToFavorites(product: Product) {
            viewModelScope.launch {
                productRepository.addProductToFavorites(product)
            }
        }

        fun removeProductFromFavorites(product: Product) {
            viewModelScope.launch {
                productRepository.removeProductFromFavorites(product)
            }
        }

    }
