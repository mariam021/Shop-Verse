package com.example.shopverse.presentation.signup

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
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

        observeViewModel()
        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        usernameFocusListener()
        setupListeners()
        requireActivity().onBackPressedDispatcher.addCallback(){
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun phoneFocusListener() {
        binding.etPhone.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.textFieldPhoneRegister.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = binding.etPhone.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 11)
        {
            return "Must be 11 Digits"
        }
        return null
    }

    private fun passwordFocusListener() {

        binding.etPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.textFieldPasswordRegister.helperText = validPassword()
            }
        }

    }

    private fun validPassword(): String? {
        val passwordText = binding.etPassword.text.toString()
        if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }

    private fun usernameFocusListener() {
        binding.etUserName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.textFieldUsername.helperText = validUsername()
            }
        }
    }

    private fun validUsername(): String? {
        val usernameText = binding.etUserName.text.toString()
        if (usernameText.isEmpty()) {
            return "Username is required"
        }
        return null
    }

    private fun emailFocusListener() {
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.textFieldEmailRegister.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            submitForm()
        }

        binding.SignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun submitForm() {
        binding.textFieldEmailRegister.helperText = validEmail()
        binding.textFieldPasswordRegister.helperText = validPassword()
        binding.textFieldPhoneRegister.helperText = validPhone()
        binding.textFieldUsername.helperText = validUsername()  // Add this line

        val validEmail = binding.textFieldEmailRegister.helperText == null
        val validPassword = binding.textFieldPasswordRegister.helperText == null
        val validPhone = binding.textFieldPhoneRegister.helperText == null
        val validUsername = binding.textFieldUsername.helperText == null  // Add this line

        if (validEmail && validPassword && validPhone && validUsername)
            resetForm()
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.textFieldEmailRegister.helperText != null)
            message += "\n\nEmail: " + binding.textFieldEmailRegister.helperText
        if(binding.textFieldPasswordRegister.helperText != null)
            message += "\n\nPassword: " + binding.textFieldPasswordRegister.helperText
        if(binding.textFieldPasswordRegister.helperText != null)
            message += "\n\nPhone: " + binding.textFieldPasswordRegister.helperText
        if(binding.textFieldUsername.helperText != null)
            message += "\n\nPhone: " + binding.textFieldUsername.helperText
    }

    private fun resetForm() {
        var message = "Email: " + binding.etEmail.text
        message += "\nPassword: " + binding.etPassword.text
        message += "\nPhone: " + binding.etPhone.text
        Toast.makeText(requireContext(), "Register succeed", Toast.LENGTH_SHORT).show()
        viewModel.signUp(binding.etUserName.text.toString(), binding.etEmail.text.toString(), binding.etPassword.text.toString(), binding.etPhone.text.toString())
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

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


}