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
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel


class LoginFragment : Fragment() {
    private lateinit var loginBtn: Button
    private lateinit var emailEt:EditText
    private lateinit var passwrodEt:EditText
    private lateinit var registertv:TextView
    private lateinit var bar:ProgressBar
    private val viewModel:AuthViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginBtn = view.findViewById(R.id.loginBtn)
        emailEt = view.findViewById(R.id.login_email_et)
        passwrodEt = view.findViewById(R.id.login_password_et)
        registertv = view.findViewById(R.id.register_fragment_textView)
        bar = view.findViewById(R.id.loginProgressBar)
        bar.visibility = View.GONE
        loginBtn.setOnClickListener{
            bar.visibility = View.VISIBLE
            viewModel.loginUser(it,emailEt.text.toString(),passwrodEt.text.toString())
            bar.visibility = View.GONE
        }
        registertv.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            it.findNavController().navigate(action)
            Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
        }
        return view
    }


}