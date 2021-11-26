package com.example.memeuniverse.ui.memes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import com.example.memeuniverse.R
import com.example.memeuniverse.ui.memes.viewmodels.MemeViewModel


class HomeScreenFragment : Fragment() {
    private lateinit var nextBtn: Button
    private val model:MemeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        nextBtn = view.findViewById(R.id.fetchmemesbtn)
        model.fetchMemes().observe(viewLifecycleOwner,{
            Log.d("memes", "onResponse: $it")
        })
        nextBtn.setOnClickListener {
            model.fetchMemes()
            Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
        }
        return view
    }

}