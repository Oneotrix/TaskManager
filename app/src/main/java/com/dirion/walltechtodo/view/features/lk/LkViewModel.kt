package com.dirion.walltechtodo.view.features.lk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllUserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LkViewModel(
    private val userInfo: UseCaseGetAllUserInfo
): ViewModel() {

    private val _state = MutableStateFlow(USER())
    val state = _state.asStateFlow()

    init {
        fetchAllUserData()
    }

    private fun fetchAllUserData() = viewModelScope.launch {
        _state.update {
            userInfo()
        }
    }


}