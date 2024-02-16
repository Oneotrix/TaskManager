package com.dirion.walltechtodo.view.ui.data_time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.databinding.FragmentDataTimeBinding

class DataTimeFragment: Fragment(){

    private lateinit var binding: FragmentDataTimeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDataTimeBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        fun newInstance() = DataTimeFragment()
    }

}