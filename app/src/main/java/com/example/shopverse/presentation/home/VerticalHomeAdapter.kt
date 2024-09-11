package com.example.shopverse.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopverse.R
import com.example.shopverse.data.local.product.Product

class VerticalHomeAdapter(
    private var products: List<Product>,
    private val onFavoriteClick: (Product) -> Unit,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<VerticalHomeAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tv_title)
        val productPrice: TextView = itemView.findViewById(R.id.tv_price)
        val productRate: TextView = itemView.findViewById(R.id.tv_rate)
        val productImage: ImageView = itemView.findViewById(R.id.img_product)
        val favoriteButton: ImageView = itemView.findViewById(R.id.btn_favorite)
    }

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.title
        holder.productPrice.text = "${product.price}$"
        holder.productRate.text = product.rating.toString()

        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .placeholder(R.drawable.error_placeholder)
            .error(R.drawable.error_placeholder)
            .into(holder.productImage)

        holder.favoriteButton.setImageResource(
            if (product.isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline_
        )

        holder.favoriteButton.setOnClickListener {
            onFavoriteClick(product)
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    override fun getItemCount(): Int = products.size
}