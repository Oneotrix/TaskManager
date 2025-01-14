package com.dirion.walltechtodo.view.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dirion.walltechtodo.MainActivity

open class BaseFragment<T: ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> T
): Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        return binding.root
    }

    override fun onStart() {
        (requireActivity() as MainActivity).hideTabLayout()
        super.onStart()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}