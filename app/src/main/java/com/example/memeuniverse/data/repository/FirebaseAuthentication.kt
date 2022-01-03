package com.example.memeuniverse.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.memeuniverse.data.daos.UserDao
import com.example.memeuniverse.data.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthentication {

    private val userDao : UserDao = UserDao()
    private val mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    private val userAuthState : MutableLiveData<Boolean> = MutableLiveData()
    init {
        if(mAuth.currentUser != null) {
            userAuthState.value = true
        }
    }
    fun getUserAuthState() : MutableLiveData<Boolean> {
        return userAuthState
    }

    suspend fun login(email:String, password:String):MutableLiveData<Boolean> {

        val authResult: AuthResult? = mAuth.signInWithEmailAndPassword(email,password).await()

        return if(authResult!=null) {
            userAuthState.value = true
//            Log.d(TAG, "login:" + userAuthState.value)
            userAuthState
        } else {
            userAuthState.value = false
            userAuthState
        }
    }

    suspend fun register(firstName:String,lastName:String,email: String,password: String): MutableLiveData<Boolean>{

        val authResult : AuthResult? = mAuth.createUserWithEmailAndPassword(email,password).await()
        return if(authResult != null) {
            val user: User = User(firstName,lastName,authResult.user?.uid,email,password)
            userDao.saveUserDetailsToFirebase(user)
            userAuthState.value = true
            userAuthState
        }
        else {
            userAuthState.value = false
            userAuthState
        }
    }
    suspend fun logout() {
        mAuth.signOut()
        userAuthState.value = false
    }
}