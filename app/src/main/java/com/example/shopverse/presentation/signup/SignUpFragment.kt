package com.example.shopverse.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopverse.R
import com.example.shopverse.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    var _binding: FragmentSignUpBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: SignUpFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val viewModelFactory = SignUpFragmentVMFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[SignUpFragmentVM::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeViewModel()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val username = binding.textFieldUsername.editText?.text.toString()
            val email = binding.textFieldEmailRegister.editText?.text.toString()
            val password = binding.textFieldPasswordRegister.editText?.text.toString()
            val phone = binding.textFieldPhoneRegister.editText?.text.toString()
            if (validation(username, email, password, phone)) {
                viewModel.signUp(username, email, password, phone)
            }
        }

        binding.SignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.signupResult.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), R.string.signup_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validation(userName: String, email: String, password: String, phone: String): Boolean {
        val isPasswordValid = Regex("^(?=.[A-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%*?&]{8,}$")
        val iEmailValid = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (userName.isEmpty()  ||email.isEmpty()  ||password.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_LONG).show()
            return false
        }
        else if (phone.length != 11) {
            Toast.makeText(requireContext(), "Phone must be 11 numbers", Toast.LENGTH_LONG).show()
            return false
        }
        else if(!isPasswordValid.matches(password)) {
            Toast.makeText(requireContext(), "The password should be combination of uppercase letters, lowercase letters, digits, and special characters with a minimum length of 8 characters", Toast.LENGTH_LONG).show()
            return false
        }
        else if (!iEmailValid.matches(email)) {
            Toast.makeText(requireContext(), "Please insert a correct email", Toast.LENGTH_LONG).show()
            return false
        }
        else {
            return true
        }
    }
}