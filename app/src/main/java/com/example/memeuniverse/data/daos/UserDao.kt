package com.example.memeuniverse.data.daos

import android.text.LoginFilter
import android.util.Log
import com.example.memeuniverse.data.models.User
import com.example.memeuniverse.ui.authentication.LoginFragmentDirections
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserDao {
    //register,login
    private val mAuth = FirebaseAuth.getInstance()
    private val db  = FirebaseFirestore.getInstance()

    suspend fun saveUserDetailsToFirebase(user:User) {
        try {
            mAuth.currentUser?.let {
                db.collection("User")
                    .document(it.uid)
                    .set(user)
                    .await()
            }
        }catch (e:Exception) {
            Log.d("FirestoreUserMessage", "saveUserDetailsToFirebase: "+e.message.toString())
        }
    }
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

    suspend fun logout() {
        return try {
            mAuth.signOut()
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }
}