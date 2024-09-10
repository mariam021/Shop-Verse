package com.example.shopverse.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopverse.R
import com.example.shopverse.data.local.product.ProductDatabase
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.FragmentHomeBinding
import com.example.shopverse.databinding.FragmentSearchBinding
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.domain.repo.user.UserRepository
import com.example.shopverse.presentation.home.HomeFragmentDirections
import com.example.shopverse.presentation.home.HomeVM
import com.example.shopverse.presentation.home.ProductViewModelFactory
import java.util.ArrayList
import java.util.Locale

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchVM
    private lateinit var searchAdapter: SearchAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productRepository =
            ProductRepository(ProductDatabase.getProductDatabase(requireContext()).productDao())

        val viewModelFactory = SearchVMFactory(productRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchVM::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize searchAdapter with an empty list or initial data
        searchAdapter = SearchAdapter(emptyList(),
            onItemClick = { product ->
                val action = SearchFragmentDirections.actionSearchFragmentToItemFragment(
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
                searchAdapter.setFilteredList(products)
            }
        }

        binding.recyclerView.adapter = searchAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        viewModel.fetchProducts()
    }

    private fun filterList(query: String?) {
        val productList = viewModel.products.value ?: emptyList()

        if (query != null && productList.isNotEmpty()) {
            val filteredList = productList.filter {
                it.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                searchAdapter.setFilteredList(filteredList)
            }
        } else {
            Toast.makeText(
                requireContext(),
                "No products available or invalid query",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}