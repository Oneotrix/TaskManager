package com.dirion.walltechtodo.view.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.databinding.FragmentSettingsBinding
import com.dirion.walltechtodo.view.features.BaseFragment

class SettingsFragment: BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

}