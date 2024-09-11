package com.example.shopverse.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopverse.R
import com.example.shopverse.data.local.product.ProductDao
import com.example.shopverse.data.local.product.ProductDatabase
import com.example.shopverse.databinding.FragmentHomeBinding
import com.example.shopverse.domain.repo.product.ProductRepository


import com.bumptech.glide.Glide
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.data.remote.ProductModule
import com.example.shopverse.domain.repo.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeVM
    private lateinit var productAdapter: VerticalHomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userRepository =
            UserRepository(UserDatabase.getUserDatabase(requireContext()).userDao())
        val productRepository =
            ProductRepository(ProductDatabase.getProductDatabase(requireContext()).productDao())
        val viewModelFactory = ProductViewModelFactory(productRepository, userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeVM::class.java]
        viewModel.loggedInUserName.observe(viewLifecycleOwner) { username ->
            binding.tvUser.text = username ?: "Guest"
        }

        viewModel.fetchLoggedInUserName()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productAdapter = VerticalHomeAdapter(emptyList(),
            onFavoriteClick = { product ->
                if (product.isFavorite) {
                    viewModel.removeProductFromFavorites(product)
                } else {
                    viewModel.addProductToFavorites(product)
                }
            },
            onItemClick = { product ->
                val action = HomeFragmentDirections.actionHomeFragmentToItemFragment(
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
                    weight = product.weight
                )
                findNavController().navigate(action)
            }
        )

        viewModel.products.observe(viewLifecycleOwner) { products ->
            if (products.isNullOrEmpty()) {
                binding.emptyStateContainer.visibility = View.VISIBLE

            } else {
                binding.emptyStateContainer.visibility = View.GONE
                productAdapter.updateProducts(products)
            }
        }


        binding.recyclerView.adapter = productAdapter

        viewModel.fetchProducts()
        requireActivity().onBackPressedDispatcher.addCallback(){
            showExitDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showExitDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Exit App")
            setMessage("Are you sure you want to exit?")
            setPositiveButton("Exit") { _, _ ->
                requireActivity().finish()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            setCancelable(true)
            show()
        }
    }
}