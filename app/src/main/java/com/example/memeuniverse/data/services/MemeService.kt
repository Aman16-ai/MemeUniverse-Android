package com.example.memeuniverse.data.services

import com.example.memeuniverse.data.models.Meme
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://meme-api.herokuapp.com/"
interface MemeInterface {
//    @GET("gimme")
//    fun getAllMemes():Call<Meme>

    @GET("gimme")
    suspend fun getAllMemes():Response<Meme>
}
object MemeService {
    val memeInstance:MemeInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        memeInstance = retrofit.create(MemeInterface::class.java)
    }
}

