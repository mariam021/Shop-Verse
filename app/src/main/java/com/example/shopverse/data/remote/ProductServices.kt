package com.example.shopverse.data.remote

import com.example.shopverse.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductServices {
    @GET("products")
    suspend fun getAllProducts(): ProductResponse
}