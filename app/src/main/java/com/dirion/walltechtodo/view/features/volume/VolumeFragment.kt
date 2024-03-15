package com.dirion.walltechtodo.view.features.volume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.databinding.FragmentVolumeBinding

class VolumeFragment: Fragment(){

    private lateinit var binding: FragmentVolumeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVolumeBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        fun newInstance() = VolumeFragment()
    }

}