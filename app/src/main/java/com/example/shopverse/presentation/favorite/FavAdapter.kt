package com.example.shopverse.presentation.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopverse.R
import com.example.shopverse.data.local.product.Product

class FavAdapter(var favList: List<Product>, private val onRemoveClick: (Product) -> Unit): RecyclerView.Adapter<FavAdapter.FavViewHolder>() {
    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productNameTextView)
        val productPrice: TextView = itemView.findViewById(R.id.productPriceTextView)
        val productStock: TextView = itemView.findViewById(R.id.productStockTextView)
        val productImage: ImageView = itemView.findViewById(R.id.productImageView)
        val productRemove: ImageButton = itemView.findViewById(R.id.btnTrashBin)
    }

    fun updateProducts(newProducts: List<Product>) {
        favList = newProducts
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.FavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_item, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavAdapter.FavViewHolder, position: Int) {
        val favItem = favList[position]

        holder.productName.text = favItem.title
        holder.productPrice.text = favItem.price.toString()
        holder.productStock.text = favItem.stock.toString()
        Glide.with(holder.itemView.context)
            .load(favItem.thumbnail)
            .into(holder.productImage)

        holder.productRemove.setOnClickListener {
            onRemoveClick(favItem)

            // Remove the item from the list and notify the adapter
            favList = favList.toMutableList().apply {
                removeAt(position)
            }
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, favList.size)  // To rebind remaining items if needed
        }
    }

    override fun getItemCount(): Int = favList.size
}