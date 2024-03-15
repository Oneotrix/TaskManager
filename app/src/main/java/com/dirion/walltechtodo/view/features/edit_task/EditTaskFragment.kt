package com.dirion.walltechtodo.view.features.edit_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.databinding.FragmentEditTaskBinding

class EditTaskFragment: Fragment(){

    private lateinit var binding: FragmentEditTaskBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditTaskBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        fun newInstance() = EditTaskFragment()
    }

}