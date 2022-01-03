package com.example.memeuniverse.ui.memes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memeuniverse.R
import com.example.memeuniverse.data.models.Meme
import com.google.firebase.firestore.QueryDocumentSnapshot

class MyMemesAdapter(val context: Context) : RecyclerView.Adapter<MyMemesAdapter.myViewHolder>() {
    private val memesList:MutableList<QueryDocumentSnapshot> = ArrayList()


    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memeImgView:ImageView = itemView.findViewById(R.id.memeImageView)
        val authortextview: TextView = itemView.findViewById(R.id.memeAuthorTextView)
        val titleTextView : TextView = itemView.findViewById(R.id.memeTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.my_memes_list,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        Glide.with(context).load(memesList[position].getString("url")).into(holder.memeImgView)
        Log.d("adapterlog", "onBindViewHolder: "+memesList[position].getString("url"))
        holder.authortextview.text = "Author :" + memesList[position].getString("author")
        holder.titleTextView.text = "Title :" + memesList[position].getString("title")

    }
    fun updateAdapter(updatedMemesList:MutableList<QueryDocumentSnapshot>) {
        memesList.clear()
        memesList.addAll(updatedMemesList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return memesList.size
    }
}