package com.dirion.walltechtodo.view.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.usecase.UseCaseLogin
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val useCaseLogin: UseCaseLogin
) : ViewModel() {

    private val _events = MutableSharedFlow<Event>()
    val events = _events.asSharedFlow()

    fun login(username: String, password: String) =  viewModelScope.launch {
        val result = useCaseLogin.login(username, password)

        when(result) {
            is BaseDomainModel.Success -> {
                _events.emit(Event.LoginSuccess)
            }
            else -> {
                _events.emit(Event.LoginError(result.message.orEmpty()))
            }
        }
    }

    sealed class Event {
        object LoginSuccess : Event()
        class LoginError(val message: String) : Event()
    }
}