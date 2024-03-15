package com.dirion.walltechtodo.view.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(){

    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

}