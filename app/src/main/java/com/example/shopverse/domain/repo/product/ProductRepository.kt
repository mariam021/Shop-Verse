package com.example.shopverse.domain.repo.product

import android.util.Log
import com.example.shopverse.data.local.product.Product
import com.example.shopverse.data.local.product.ProductDao
import com.example.shopverse.data.remote.ProductModule.apiService

class ProductRepository(private val productDao: ProductDao) {
    suspend fun fetchAndSaveProducts() {
        try {
            val products = apiService.getAllProducts()
            val favoriteProducts = productDao.getFavoriteProducts()
            val favoriteMap = favoriteProducts.associateBy { it.id }
            val mergedProducts = products.products.map { product ->
                product.isFavorite = favoriteMap.containsKey(product.id)
                product
            }
            if (mergedProducts.isNotEmpty()) {
                productDao.insertAllProducts(mergedProducts)
            }
        } catch (e: Exception) {
            Log.d("api error", e.toString())
        }
    }

    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend fun getFavoriteProducts(): List<Product> {
        return productDao.getFavoriteProducts()
    }

    suspend fun addProductToFavorites(product: Product) {
        product.isFavorite = true
        productDao.updateProduct(product)
        productDao.insertProduct(product)
    }

    suspend fun removeProductFromFavorites(product: Product) {
        product.isFavorite = false
        productDao.updateProduct(product)
        productDao.insertProduct(product)
    }

    suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }
}