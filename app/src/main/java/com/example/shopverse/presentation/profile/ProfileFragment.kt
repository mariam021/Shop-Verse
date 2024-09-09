package com.example.shopverse.presentation.profile
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopverse.R
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.FragmentProfileBinding
import com.example.shopverse.presentation.EnteryActivity
import com.example.shopverse.presentation.NavigationDestination
import com.example.shopverse.presentation.login.LoginFragment

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
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
        val userDao = UserDatabase.getUserDatabase(requireContext()).userDao()
        val viewModelFactory = ProfileViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileVM::class.java)
        viewModel.loadUser()


        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.email.text = user?.email.toString()
            binding.userName.text = user?.userName.toString()
            binding.userPhone.text = user?.phone.toString()
            binding.userPassword.text = user?.password.toString()

        }
        binding.btnSignOut.setOnClickListener {
            logoutUser()
        }

    }
    private fun logoutUser() {
        val bundle = Bundle().apply {
            putSerializable("navigationSource", NavigationDestination.ProfileFragment)
        }
        val intent = Intent(requireActivity(), EnteryActivity::class.java).apply {
            putExtras(bundle)
        }

        startActivity(intent)
    }

    override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
}







