package com.example.shopverse.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopverse.data.local.product.ProductDatabase
import com.example.shopverse.databinding.FragmentFavoriteBinding
import com.example.shopverse.databinding.FragmentItemBinding
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.presentation.entry.NavigationDestination
import com.example.shopverse.presentation.home.HomeFragmentDirections
import com.example.shopverse.presentation.home.HomeVM


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favVM: FavVM
    private lateinit var favAdapter: FavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository =
            ProductRepository(ProductDatabase.getProductDatabase(requireContext()).productDao())
        val viewModelFactory = FavViewModelFactory(repository)

        favVM = ViewModelProvider(this, viewModelFactory)[FavVM::class.java]
        binding.rvFav.layoutManager = LinearLayoutManager(requireContext())

        favAdapter = FavAdapter(emptyList(),
            onRemoveClick = { product ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirm Removal")
                    .setMessage("Are you sure you want to remove this item from your favorites?")
                    .setPositiveButton("Delete") { _, _ ->
                        favVM.removeProductFromFavorites(product)
                        favAdapter.updateProducts(favAdapter.favList.filter { it.id != product.id })
                        if (favAdapter.favList.isEmpty()) {
                            binding.imgEmptyFav.visibility = View.VISIBLE
                            binding.rvFav.visibility = View.GONE
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            },
            onItemClick = { product ->
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToItemFragment(
                    images = product.images.toTypedArray(),
                    title = product.title,
                    description = product.description,
                    category = product.category,
                    availabilityStatus = product.availabilityStatus,
                    discountPercentage = product.discountPercentage.toFloat(),
                    price = product.price.toFloat(),
                    warrantyInformation = product.warrantyInformation,
                    stock = product.stock,
                    rating = product.rating.toFloat(),
                    weight = product.weight,
                    navigationSource = NavigationDestination.FavouriteFragment
                )
                findNavController().navigate(action)
            }
        )

        binding.rvFav.adapter = favAdapter
        favVM.products.observe(viewLifecycleOwner) { products ->
            if (products.isNullOrEmpty()){
                binding.imgEmptyFav.visibility = View.VISIBLE
            }
            else {
                binding.imgEmptyFav.visibility = View.GONE
                favAdapter.updateProducts(products)
            }
        }
        favVM.getFavoriteProducts()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}