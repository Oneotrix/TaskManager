package com.dirion.walltechtodo.view.features.login

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.usecase.UseCaseLogin


class LoginViewModel(
    private val useCaseLogin: UseCaseLogin
) : ViewModel() {

    suspend fun login(username: String, password: String): LoginState {
        val result = useCaseLogin.login(username, password)

        when(result) {
            is BaseDomainModel.Success -> {
                return LoginState.Success()
            }
            else -> {
                return LoginState.Error(result.message.orEmpty())
            }
        }
    }

    sealed class LoginState(message: String? = null) {
        class Success : LoginState()

        class Error(val message: String) : LoginState(message)
    }
}