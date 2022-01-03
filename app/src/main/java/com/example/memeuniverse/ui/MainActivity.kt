package com.example.memeuniverse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.memeuniverse.R
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private val auth:FirebaseAuth = FirebaseAuth.getInstance()
//    override fun onStart() {
//        super.onStart()
//        if(auth.currentUser!= null) {
//            navController.navigate(R.id.action_loginFragment_to_homeScreenFragment)
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = resources.getColor(R.color.black)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController

    }
}


