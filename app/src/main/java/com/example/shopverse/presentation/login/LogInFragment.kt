package com.example.shopverse.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopverse.R
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.FragmentLogInBinding
import com.example.shopverse.databinding.FragmentSignUpBinding
import com.example.shopverse.domain.repo.user.UserRepository
import com.example.shopverse.presentation.entry.EntryVM
import com.example.shopverse.presentation.entry.EntryVMFactory
import com.example.shopverse.presentation.main.MainActivity

class LoginFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val viewModelFactory = LoginFragmentVMFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginFragmentVM::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeViewModel()
        requireActivity().onBackPressedDispatcher.addCallback(){
            requireActivity().finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.textFieldEmailLogin.editText?.text.toString()
            val password = binding.textFieldPasswordLogin.editText?.text.toString()
            viewModel.login(email, password)
        }

        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                // Navigate to MainActivity if login is successful
                val intent = Intent(requireActivity(), MainActivity::class.java)
                Toast.makeText(requireContext(), "Logged in successfully", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), R.string.login_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
