package com.example.memeuniverse.ui.memes.viewmodels

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.memeuniverse.data.daos.MemeDao
import com.example.memeuniverse.data.models.Meme
import com.example.memeuniverse.data.services.MemeService
import com.example.memeuniverse.ui.memes.HomeScreenFragment
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeViewModel(application: Application):AndroidViewModel(application) {

    private  var meme:MutableLiveData<Meme> = MutableLiveData()
    private  var dao:MemeDao = MemeDao()
    private var mymemes:MutableLiveData<List<QueryDocumentSnapshot>?> = MutableLiveData()
    private var myMemesList : List<QueryDocumentSnapshot>? = ArrayList()
    fun fetchmymemes() : MutableLiveData<List<QueryDocumentSnapshot>?> {
        viewModelScope.launch {
            myMemesList = dao.getSavedMemes()?.toList()
            mymemes.value = myMemesList
        }
        return mymemes
    }

    public fun saveMeme(meme: Meme) {
        viewModelScope.launch {
            if(dao.saveMemeToFireStore(meme)) {
                Toast.makeText(getApplication(),"Meme saved",Toast.LENGTH_SHORT).show()
            }
        }
    }
    public fun fetchMemes() : MutableLiveData<Meme> {

        viewModelScope.launch {
            val response : Response<Meme> = getMemes()
            meme.value = response.body()
        }
        return meme
    }
    suspend fun getMemes():Response<Meme> {
        return withContext(Dispatchers.IO) {
            MemeService.memeInstance.getAllMemes()
        }
    }

    fun sharememe(context: Context, meme:Meme) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,meme.url)
        }
        context.startActivity(Intent.createChooser(intent,"share"))
    }


}