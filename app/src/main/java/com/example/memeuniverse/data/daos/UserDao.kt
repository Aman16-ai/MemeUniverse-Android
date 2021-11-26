package com.example.memeuniverse.data.daos

import android.text.LoginFilter
import com.example.memeuniverse.data.models.User
import com.example.memeuniverse.ui.authentication.LoginFragmentDirections
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserDao {
    //register,login
    private val mAuth = FirebaseAuth.getInstance()
     suspend fun login(email:String, password:String): AuthResult? {
         return try{
             mAuth.signInWithEmailAndPassword(email,password).await()
         }catch (e:Exception) {
             return null
         }

    }
    suspend fun register(email: String,password: String): AuthResult?{
        return try {
            mAuth.createUserWithEmailAndPassword(email,password).await()
        }
        catch (e:Exception) {
            return null
        }
    }
}