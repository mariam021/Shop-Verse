package com.example.shopverse.presentation.itemDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopverse.databinding.FragmentItemBinding
import com.example.shopverse.presentation.entry.NavigationDestination
import kotlinx.coroutines.launch

class ItemFragment : Fragment() {
    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var navigationSource: NavigationDestination

    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        val args = ItemFragmentArgs.fromBundle(requireArguments())
        navigationSource = args.navigationSource
        itemViewModel.setItemDetails(
            args.title, args.description, args.category, args.availabilityStatus,
            args.discountPercentage, args.price, args.warrantyInformation,
            args.stock, args.rating, args.weight, args.images.toList()
        )
        itemViewModel.itemDetails.observe(viewLifecycleOwner) { itemDetails ->
            binding.tvDetailsTitle.text = itemDetails.title
            binding.tvDetailsDescription.text = itemDetails.description
            binding.tvDetailsCategory.text = itemDetails.category
            binding.tvDetailsAvailabilityStatus.text = itemDetails.availabilityStatus
            binding.tvDetailsDiscountPercentage.text = itemDetails.discountPercentage.toString()
            binding.tvDetailsPrice.text = "${itemDetails.price}$"
            binding.tvDetailsWarrantyInformation.text = itemDetails.warrantyInformation
            binding.tvDetailsStock.text = itemDetails.stock.toString()
            binding.tvDetailsRating.text = itemDetails.rating.toString()
            binding.tvDetailsWeigh.text = itemDetails.weight.toString()

            val imageAdapter = HorizontalItemAdapter(itemDetails.images)
            binding.rvDetailsImages.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = imageAdapter
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPress()
            }
        })
    }

    private fun handleBackPress() {
        when (navigationSource) {
            NavigationDestination.HomeFragment -> {
                val action = ItemFragmentDirections.actionItemFragmentToHomeFragment()
                navController.navigate(action)
            }
            NavigationDestination.SearchFragment -> {
                val action = ItemFragmentDirections.actionItemFragmentToSearchFragment()
                navController.navigate(action)
            }
            NavigationDestination.FavouriteFragment -> {
                val action = ItemFragmentDirections.actionItemFragmentToFavoriteFragment()
                navController.navigate(action)
            }
            else -> {
                navController.popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
