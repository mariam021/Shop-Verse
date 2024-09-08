package com.example.shopverse.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shopverse.R
import com.example.shopverse.databinding.FragmentLogInBinding
import com.example.shopverse.databinding.FragmentSplashBinding
import com.example.shopverse.presentation.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences("shopverse_prefs", Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        lifecycleScope.launch {
            delay(1200)
            if (isFirstLaunch) {
                // Navigate to the welcome screen
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            } else {
                viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
                    if (isLoggedIn) {
                        Toast.makeText(requireContext(), "loggedIn", Toast.LENGTH_SHORT).show()
                        // Navigate to the home fragment
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Navigate to the login fragment
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    }
                }

                viewModel.checkLoginStatus()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}