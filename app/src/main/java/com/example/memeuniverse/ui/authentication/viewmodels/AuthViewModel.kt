package com.example.memeuniverse.ui.authentication.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.memeuniverse.data.daos.UserDao
import com.example.memeuniverse.data.models.User
import com.example.memeuniverse.ui.authentication.LoginFragment
import com.example.memeuniverse.ui.authentication.LoginFragmentDirections
import com.example.memeuniverse.ui.authentication.RegisterFragmentDirections
import com.example.memeuniverse.ui.memes.HomeScreenFragment
import com.example.memeuniverse.ui.memes.HomeScreenFragmentDirections
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel(application: Application):AndroidViewModel(application) {
    private val dao:UserDao = UserDao()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    fun checkUserState(view:View) {
        Log.d("userstate", "checkUserState: "+auth.currentUser)
        if (auth.currentUser != null) {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeScreenFragment()
            view.findNavController().navigate(action)
        }
    }
    fun loginUser(view: View, email:String, password:String) {
        viewModelScope.launch {
            val result: AuthResult? = dao.login(email,password)
            if(result != null) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeScreenFragment()
                view.findNavController().navigate(action)
            }
            else {
                Toast.makeText(getApplication(),"login failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun registerUser(view:View,
                     email: String,
                     password: String,
                     firstName:String,
                     lastName:String) {
       viewModelScope.launch {
           val result:AuthResult?=dao.register(email,password)
           result?.let {
               val user = User(firstName,lastName, it.user?.uid,email,password)
               dao.saveUserDetailsToFirebase(user)
               val action = RegisterFragmentDirections.actionRegisterFragmentToHomeScreenFragment()
               view.findNavController().navigate(action)
           }
       }
    }
    fun logoutUser(view:View) {
        viewModelScope.launch {
            dao.logout()

            Toast.makeText(getApplication(),"Logout",Toast.LENGTH_SHORT).show()
        }
    }
}