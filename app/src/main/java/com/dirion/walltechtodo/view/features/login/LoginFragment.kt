package com.dirion.walltechtodo.view.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentLoginBinding
import com.dirion.walltechtodo.view.features.login.LoginViewModel.LoginState.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        App.presentationComponent.loginFragmentComponentBuilder()
            .build()
            .inject(this)


        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        setupLoginClickListener()
    }

    private fun setupLoginClickListener() {

        binding.btnLoggin.setOnClickListener {
            val username = binding.textInputUsername.text.toString()
            val password = binding.textInputPassword.text.toString()

            lifecycleScope.launch {
                val auth = viewModel.login(username, password)

                when(auth) {
                    is Success -> {
                        navigateToTasks()
                    }

                    is Error -> {
                        showErrorSnackbar(auth.message)
                    }
                }
            }

        }
    }

    private fun navigateToTasks() {
        MainActivity.activityComponent.navigationController()
            .navigate(R.id.action_loginFragment_to_tasksFragment)
    }

    private fun showErrorSnackbar(message: String) {
        Snackbar
            .make(binding.root, message, Snackbar.LENGTH_LONG)
            .show()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}