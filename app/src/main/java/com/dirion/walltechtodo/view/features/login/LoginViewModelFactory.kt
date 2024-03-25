package com.dirion.walltechtodo.view.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseLogin
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val useCaseLogin : UseCaseLogin
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            useCaseLogin = useCaseLogin
        ) as T
    }
}