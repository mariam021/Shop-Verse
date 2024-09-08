package com.example.shopverse.data.local.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product(

    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val images: List<String>,
    val category: String,
    val availabilityStatus: String,
    val discountPercentage: Double,
    val price: Double,
    val warrantyInformation: String,
    val stock: Int,
    val rating: Double,
    val thumbnail: String,
    val weight: Int,
    var isFavorite: Boolean = false
)

