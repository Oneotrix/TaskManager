package com.dirion.walltechtodo.view.features.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.databinding.FragmentNotificationsBinding

class NotificationsFragment: Fragment(){

    private lateinit var binding: FragmentNotificationsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        fun newInstance() = NotificationsFragment()
    }

}