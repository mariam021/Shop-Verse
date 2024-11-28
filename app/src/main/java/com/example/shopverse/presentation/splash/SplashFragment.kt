package com.example.shopverse.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    var _binding: FragmentSplashBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: SplashFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val viewModelFactory = SplashFragmentVMFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashFragmentVM::class.java]
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}