package com.example.shopverse.presentation.itemDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {

    private val _itemDetails = MutableLiveData<ItemDetails>()
    val itemDetails: LiveData<ItemDetails> get() = _itemDetails

    fun setItemDetails(
        title: String,
        description: String,
        category: String,
        availabilityStatus: String,
        discountPercentage: Float,
        price: Float,
        warrantyInformation: String,
        stock: Int,
        rating: Float,
        weight: Int,
        images: List<String>
    ) {
        _itemDetails.value = ItemDetails(
            title, description, category, availabilityStatus, discountPercentage,
            price, warrantyInformation, stock, rating, weight, images
        )
    }

    data class ItemDetails(
        val title: String,
        val description: String,
        val category: String,
        val availabilityStatus: String,
        val discountPercentage: Float,
        val price: Float,
        val warrantyInformation: String,
        val stock: Int,
        val rating: Float,
        val weight: Int,
        val images: List<String>
    )
}
