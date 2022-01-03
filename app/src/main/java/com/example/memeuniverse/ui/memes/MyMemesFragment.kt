package com.example.memeuniverse.ui.memes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memeuniverse.R
import com.example.memeuniverse.databinding.FragmentMyMemesBinding
import com.example.memeuniverse.ui.memes.viewmodels.MemeViewModel
import com.google.api.Distribution
import com.google.firebase.firestore.QueryDocumentSnapshot


class MyMemesFragment : Fragment() {


    private val viewmodel:MemeViewModel by viewModels()
    private var _binding:FragmentMyMemesBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyMemesBinding.inflate(inflater)
        val view = binding.root

       var madapter : MyMemesAdapter = MyMemesAdapter(requireActivity())
        binding.mymemesRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = madapter
        }
        viewmodel.fetchmymemes().observe(viewLifecycleOwner) {
           if(it != null) {
               for(i in it as MutableList) {
                   Log.d("mymemesfrag", "onCreateView: "+i.getString("url"))
               }

               madapter.updateAdapter(it)
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}