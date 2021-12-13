package com.example.memeuniverse.ui.customdialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.example.memeuniverse.R
import com.example.memeuniverse.ui.authentication.viewmodels.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class customBottomSheetDialog: BottomSheetDialogFragment() {

    private val authViewModel:AuthViewModel by viewModels()
    private lateinit var logoutButton: Button;
    private lateinit var aboutButton: Button;
    private lateinit var settingsButton:Button;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottomsheetdialog,container,false);
        logoutButton = view.findViewById(R.id.logout_btn);
        aboutButton = view.findViewById(R.id.aboutbtn);
        settingsButton = view.findViewById(R.id.settingsbtn)

        logoutButton.setOnClickListener {
            authViewModel.logoutUser(it)
        }

        return view;
    }
}