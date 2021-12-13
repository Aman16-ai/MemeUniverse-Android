package com.example.memeuniverse.ui.memes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.memeuniverse.R
import com.example.memeuniverse.data.models.Meme
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel
import com.example.memeuniverse.ui.memes.viewmodels.MemeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeScreenFragment : Fragment() {
    private lateinit var memeImgView:ImageView
    private lateinit var memetv:TextView
    private lateinit var memeauthortv:TextView
    private lateinit var nextBtn:FloatingActionButton
    private lateinit var saveMemeBtn : FloatingActionButton
    private lateinit var menubtn:FloatingActionButton
    private lateinit var sharebtn:FloatingActionButton
    private lateinit var meme: Meme
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
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        nextBtn = view.findViewById(R.id.fetchmemesbtn)
        menubtn = view.findViewById(R.id.menubtn)
        saveMemeBtn = view.findViewById(R.id.savememebtn)
        memeImgView = view.findViewById(R.id.memeImageView)
        memeauthortv = view.findViewById(R.id.memeAuthorTextView)
        sharebtn = view.findViewById(R.id.sharememebtn)
        memetv = view.findViewById(R.id.memeTitleTextView)
        model.fetchMemes().observe(viewLifecycleOwner,{
            meme = it
            Log.d("memes", "onResponse: $it")
            memeauthortv.text = "Author : " + it.author
            memetv.text = "Title : "+it.title
            Glide.with(requireContext()).load(it.url).into(memeImgView)
        })

        menubtn.setOnClickListener {
            val dialog = activity?.let { it1 -> BottomSheetDialog(it1) }
            val dialogview = layoutInflater.inflate(R.layout.bottomsheetdialog,null)
            val logoutButton:Button = dialogview.findViewById(R.id.logout_btn);
            val aboutButton:Button = dialogview.findViewById(R.id.aboutbtn);
            val settingsButton:Button = dialogview.findViewById(R.id.settingsbtn)

            logoutButton.setOnClickListener {
                authViewModel.logoutUser(it)
                val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToLoginFragment()
                view.findNavController().navigate(action)
                if (dialog != null) {
                    dialog.dismiss()
                }
            }
            if (dialog != null) {
                dialog.setContentView(dialogview)
                dialog.show()
            }

        }
        sharebtn.setOnClickListener {
            model.sharememe(requireContext(),meme)
        }
        nextBtn.setOnClickListener {
            model.fetchMemes()
        }
        saveMemeBtn.setOnClickListener {
            model.saveMeme(meme)
        }
        return view
    }


}