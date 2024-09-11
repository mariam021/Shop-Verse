package com.example.shopverse.data.model

import com.example.shopverse.data.local.product.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    val products: List<Product>
)
