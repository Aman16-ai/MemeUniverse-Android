package com.example.memeuniverse.ui.memes.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.memeuniverse.data.models.Meme
import com.example.memeuniverse.data.services.MemeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeViewModel(application: Application):AndroidViewModel(application) {

    private  var meme:MutableLiveData<Meme> = MutableLiveData()

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
}