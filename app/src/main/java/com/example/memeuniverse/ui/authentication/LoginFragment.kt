package com.example.memeuniverse.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.memeuniverse.R
import com.example.memeuniverse.databinding.FragmentLoginBinding
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel


class LoginFragment : Fragment() {
    private  var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel:AuthViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root

        binding.loginBtn.setOnClickListener{
            viewModel.loginUser(it,binding.loginEmailEt.text.toString(),binding.loginPasswordEt.text.toString())
        }
         binding.registerFragmentTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            it.findNavController().navigate(action)
            Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}