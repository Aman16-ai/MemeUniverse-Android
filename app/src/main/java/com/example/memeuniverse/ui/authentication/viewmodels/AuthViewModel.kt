package com.example.memeuniverse.ui.authentication.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.memeuniverse.data.daos.UserDao
import com.example.memeuniverse.data.models.User
import com.example.memeuniverse.data.repository.FirebaseAuthentication
import com.example.memeuniverse.ui.authentication.LoginFragment
import com.example.memeuniverse.ui.authentication.LoginFragmentDirections
import com.example.memeuniverse.ui.authentication.RegisterFragmentDirections
import com.example.memeuniverse.ui.memes.HomeScreenFragment
import com.example.memeuniverse.ui.memes.HomeScreenFragmentDirections
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel(application: Application):AndroidViewModel(application) {
//    private val dao:UserDao = UserDao()
    private val firebaseAuthenticationRepo: FirebaseAuthentication = FirebaseAuthentication()
    private var _userAuthState :MutableLiveData<Boolean> = MutableLiveData()

    fun getUserAuthState(): MutableLiveData<Boolean> {
        _userAuthState = firebaseAuthenticationRepo.getUserAuthState()
        return _userAuthState
    }
    fun loginUser(email:String, password:String) {
        viewModelScope.launch {
            _userAuthState = firebaseAuthenticationRepo.login(email,password)
            Log.d("authviewmodeltag", "loginUser: "+_userAuthState.value)
        }
    }
    fun registerUser(email: String,
                     password: String,
                     firstName:String,
                     lastName:String) {
       viewModelScope.launch {
           _userAuthState = firebaseAuthenticationRepo.register(firstName,lastName,email,password)
       }
    }
    fun logoutUser(view:View) {
        viewModelScope.launch {
            firebaseAuthenticationRepo.logout()
            Toast.makeText(getApplication(),"Logout",Toast.LENGTH_SHORT).show()
        }
    }
}