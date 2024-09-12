package com.example.shopverse.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shopverse.data.local.product.ProductDatabase
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.FragmentProfileBinding
import com.example.shopverse.domain.repo.product.ProductRepository
import com.example.shopverse.domain.repo.user.UserRepository
import com.example.shopverse.presentation.entry.EnteryActivity
import com.example.shopverse.presentation.entry.NavigationDestination
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var productRepository: ProductRepository
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: ProfileVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userRepository = UserRepository(UserDatabase.getUserDatabase(requireContext()).userDao())
        productRepository =
            ProductRepository(ProductDatabase.getProductDatabase(requireContext()).productDao())
        val viewModelFactory = ProfileViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileVM::class.java]
        viewModel.loadUser()

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.email.text = user?.email.toString()
            binding.userName.text = user?.userName.toString()
            binding.userPhone.text = user?.phone.toString()
            binding.userPassword.text = user?.password.toString()
        }

        binding.btnSignOut.setOnClickListener {
            signOutUser()
        }
    }

    private fun signOutUser() {
        lifecycleScope.launch {
            productRepository.deleteAllProducts()
            val userEmail = viewModel.user.value?.email
            if (userEmail != null) {
                userRepository.deleteUser(userEmail)
            }
            //viewModel.logoutUser()
            val bundle = Bundle().apply {
                putSerializable("navigationSource", NavigationDestination.ProfileFragment)
            }
            val intent = Intent(requireActivity(), EnteryActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}