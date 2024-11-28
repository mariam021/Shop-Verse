package com.example.shopverse.presentation.welcome

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopverse.R
import com.example.shopverse.databinding.FragmentLogInBinding
import com.example.shopverse.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    var _binding: FragmentWelcomeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("shopverse_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("isFirstLaunch", false)
            apply()
        }

        binding.buttonGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(){
            showExitDialog()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showExitDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Exit App")
            setMessage("Are you sure you want to exit?")
            setPositiveButton("Exit") { _, _ ->
                requireActivity().finish()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            setCancelable(true)
            show()
        }
    }
}