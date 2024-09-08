package com.example.shopverse.presentation.itemDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopverse.R
import com.example.shopverse.data.local.product.Product

class HorizontalItemAdapter(private val images: List<String>): RecyclerView.Adapter<HorizontalItemAdapter.HorizontalViewHolder>() {
    inner class HorizontalViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val image: ImageView = item.findViewById(R.id.img_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalItemAdapter.HorizontalViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_details_item, parent, false)
        return HorizontalViewHolder(layout)
    }

    override fun onBindViewHolder(holder: HorizontalItemAdapter.HorizontalViewHolder, position: Int) {
        val imageUrl = images[position]
        Glide.with(holder.image.context).load(imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int = images.size
}