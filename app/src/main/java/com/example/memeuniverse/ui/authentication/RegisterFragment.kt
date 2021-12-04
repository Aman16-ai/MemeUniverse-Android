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
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel


class RegisterFragment : Fragment() {


    private lateinit var RegisterBtn: Button
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var firstNameEt: EditText
    private lateinit var lastNameEt: EditText
    private lateinit var logintv: TextView

    private val viewModel:AuthViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        RegisterBtn = view.findViewById(R.id.registerBtn)
        firstNameEt = view.findViewById(R.id.register_first_name_et)
        lastNameEt = view.findViewById(R.id.register_last_name_et)
        emailEt = view.findViewById(R.id.register_email_et)
        passwordEt = view.findViewById(R.id.register_password_et)
        logintv = view.findViewById(R.id.login_fragment_textView)

        RegisterBtn.setOnClickListener{
            viewModel.registerUser(it,emailEt.text.toString(),passwordEt.text.toString(), firstNameEt.text.toString(),lastNameEt.text.toString())
        }
        logintv.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            it.findNavController().navigate(action)
        }
        return view
    }

}