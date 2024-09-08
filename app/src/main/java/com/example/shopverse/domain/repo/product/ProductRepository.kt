package com.example.shopverse.domain.repo.product

import com.example.shopverse.data.local.product.Product
import com.example.shopverse.data.local.product.ProductDao
import com.example.shopverse.data.remote.ProductModule.apiService

class ProductRepository(private val productDao: ProductDao) {
    suspend fun fetchAndSaveProducts() {
        try {
            // Fetch products from the remote API
            val products = apiService.getAllProducts()

            // Get the current favorite products from the database
            val favoriteProducts = productDao.getFavoriteProducts()
            val favoriteMap = favoriteProducts.associateBy { it.id }

            // Merge favorite status with fetched products
            val mergedProducts = products.products.map { product ->
                product.isFavorite = favoriteMap.containsKey(product.id)
                product
            }

            // Save the merged products to the database
            if (mergedProducts.isNotEmpty()) {
                productDao.insertAllProducts(mergedProducts)
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., logging)
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

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }
}