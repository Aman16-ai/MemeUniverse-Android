package com.example.memeuniverse.ui.memes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.memeuniverse.R
import com.example.memeuniverse.data.models.Meme

import com.example.memeuniverse.databinding.FragmentHomeScreenBinding
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel
import com.example.memeuniverse.ui.memes.viewmodels.MemeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class HomeScreenFragment : Fragment() {

    private lateinit var meme: Meme
    private var _binding:FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private val model:MemeViewModel by activityViewModels()
    private val authViewModel:AuthViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        model.fetchMemes().observe(viewLifecycleOwner,{
            meme = it
            Log.d("memes", "onResponse: $it")
            binding.memeAuthorTextView.text = "Author : " + it.author
            binding.memeTitleTextView.text = "Title : "+it.title
            Glide.with(requireContext()).load(it.url).into(binding.memeImageView)
        })

        binding.menubtn.setOnClickListener {
            val dialog = activity?.let { it1 -> BottomSheetDialog(it1) }
            val dialogview = layoutInflater.inflate(R.layout.bottomsheetdialog,null)
            val logoutButton:Button = dialogview.findViewById(R.id.logout_btn);
            val aboutButton:Button = dialogview.findViewById(R.id.aboutbtn);
            val settingsButton:Button = dialogview.findViewById(R.id.settingsbtn)
            val mymemesbtn:Button = dialogview.findViewById(R.id.my_memes_btn)
            mymemesbtn.setOnClickListener {
                val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToMyMemesFragment()
                view.findNavController().navigate(action)
                dialog?.dismiss()
            }
            logoutButton.setOnClickListener {
                authViewModel.logoutUser(it)
                val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToLoginFragment()
                view.findNavController().navigate(action)
                dialog?.dismiss()
            }
            if (dialog != null) {
                dialog.setContentView(dialogview)
                dialog.show()
            }

        }
        binding.sharememebtn.setOnClickListener {
            model.sharememe(requireContext(),meme)
        }
        binding.fetchmemesbtn.setOnClickListener {
            model.fetchMemes()
        }
        binding.savememebtn.setOnClickListener {
            model.saveMeme(meme)
        }
        return view
    }


}