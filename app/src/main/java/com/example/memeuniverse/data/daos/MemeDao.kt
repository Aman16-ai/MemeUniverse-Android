package com.example.memeuniverse.data.daos

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import com.example.memeuniverse.data.models.Meme
import com.example.memeuniverse.data.services.MemeService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import retrofit2.Callback
import retrofit2.Response

class MemeDao {
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    suspend fun getSavedMemes(): QuerySnapshot? {
        return mAuth.currentUser?.let {
            db.collection("MyMemes")
                .whereEqualTo("uid",it.uid)
                .get()
                .await()
        }
    }
    suspend fun saveMemeToFireStore(meme: Meme):Boolean {
         return try {
            mAuth.currentUser?.uid?.let {
                meme.uid = mAuth.currentUser!!.uid
                db.collection("MyMemes")
                    .add(meme)
                    .await()
            }
            true
        }catch (e:Exception) {
            e.printStackTrace()
            false
        }
    }
}


