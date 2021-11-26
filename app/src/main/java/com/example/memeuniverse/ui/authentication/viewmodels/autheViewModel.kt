package com.example.memeuniverse.ui.authentication.viewmodels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.memeuniverse.data.daos.UserDao
import com.example.memeuniverse.data.models.User
import com.example.memeuniverse.ui.authentication.LoginFragment
import com.example.memeuniverse.ui.authentication.LoginFragmentDirections
import com.example.memeuniverse.ui.authentication.RegisterFragmentDirections
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.launch

class autheViewModel(application: Application):AndroidViewModel(application) {
    private val dao:UserDao = UserDao()

    fun loginUser(view: View, email:String, password:String) {
        viewModelScope.launch {
            val result: AuthResult? = dao.login(email,password)
            result.let {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeScreenFragment()
                view.findNavController().navigate(action)
            }
        }
    }
    fun registerUser(view:View,email: String,password: String) {
       viewModelScope.launch {
           val result:AuthResult?=dao.register(email,password)
            result.let {
                val action = RegisterFragmentDirections.actionRegisterFragmentToHomeScreenFragment()
                view.findNavController().navigate(action)
            }
       }
    }
}