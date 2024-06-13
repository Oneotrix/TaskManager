package com.dirion.walltechtodo.view.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentLoginBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        App.taskComponent.loginFragmentComponentBuilder()
            .build()
            .inject(this)


        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        setupListeners()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.events
            .onEach { event->
                when(event) {
                    is LoginViewModel.Event.LoginSuccess -> navigateToTasks(event.role)
                    is LoginViewModel.Event.LoginError -> showErrorSnackbar("Неверный логин или пароль")
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun setupListeners() {
        setupLoginClickListener()
    }

    private fun setupLoginClickListener() {
        binding.btnLoggin.setOnClickListener {
            val username = binding.textInputUsername.text.toString()
            val password = binding.textInputPassword.text.toString()

            viewModel.login(username, password)
        }
    }

    private fun navigateToTasks(role: Int) {
        val bundle = Bundle().apply { putInt("role", role) }
        MainActivity.navigationComponent.navigationController()
            .navigate(R.id.action_loginFragment_to_tasksFragment, bundle)

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