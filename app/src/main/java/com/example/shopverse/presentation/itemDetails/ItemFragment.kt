package com.example.shopverse.presentation.itemDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopverse.R
import com.example.shopverse.databinding.FragmentItemBinding
import com.example.shopverse.presentation.home.HomeVM
import com.example.shopverse.presentation.home.VerticalHomeAdapter

class ItemFragment : Fragment() {
    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = ItemFragmentArgs.fromBundle(requireArguments())
        binding.tvDetailsTitle.text = args.title
        binding.tvDetailsDescription.text = args.description
        binding.tvDetailsCategory.text = args.category
        binding.tvDetailsAvailabilityStatus.text = args.availabilityStatus
        binding.tvDetailsDiscountPercentage.text = args.discountPercentage.toString()
        binding.tvDetailsPrice.text = args.price.toString()
        binding.tvDetailsWarrantyInformation.text = args.warrantyInformation
        binding.tvDetailsStock.text = args.stock.toString()
        binding.tvDetailsRating.text = args.rating.toString()
        binding.tvDetailsWeigh.text = args.weight.toString()

        val imageAdapter = HorizontalItemAdapter(args.images.toList())
        binding.rvDetailsImages.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = imageAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}