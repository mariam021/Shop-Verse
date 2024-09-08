package com.example.shopverse.presentation.profile
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shopverse.R
import com.example.shopverse.data.local.user.UserDatabase
import com.example.shopverse.databinding.FragmentProfileBinding
import com.example.shopverse.presentation.EnteryActivity
import com.example.shopverse.presentation.login.LoginFragment

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            binding.email.text = user?.email.toString() ?: "Email not found"
            binding.userName.text = user?.userName.toString() ?: "Username not found"
            binding.userPhone.text = user?.phone.toString() ?: " Phone not found"
            binding.userPassword.text = user?.password.toString() ?: " Password not found"

        }
        binding.btnSignOut.setOnClickListener() {
            logoutUser()
        }

    }
    private fun logoutUser() {
        viewModel.signOutUser()
        val intent = Intent(requireActivity(), EnteryActivity::class.java)
        intent.putExtra("fragment_id", R.id.loginFragment)  // Pass the fragment ID
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
}








