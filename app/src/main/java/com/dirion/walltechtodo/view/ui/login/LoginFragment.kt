package com.dirion.walltechtodo.view.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLoggin.setOnClickListener {
            MainActivity.activityComponent.navigationController()
                .navigate(R.id.action_loginFragment_to_tasksFragment)
        }

        return binding.root
    }


    companion object {
        fun newInstance() = LoginFragment()
    }
}