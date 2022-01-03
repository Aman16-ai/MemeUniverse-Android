package com.example.memeuniverse.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.memeuniverse.R
import com.example.memeuniverse.databinding.FragmentRegisterBinding
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel


class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel:AuthViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel.getUserAuthState().observe(viewLifecycleOwner) {
            if(it) {
                val action = RegisterFragmentDirections.actionRegisterFragmentToHomeScreenFragment()
                view.findNavController().navigate(action)
            }
        }
        binding.registerBtn.setOnClickListener{
            viewModel.registerUser(binding.registerEmailEt.text.toString(),binding.registerPasswordEt.text.toString(), binding.registerFirstNameEt.text.toString(),binding.registerLastNameEt.text.toString())
        }
        binding.loginFragmentTextView.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            it.findNavController().navigate(action)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}