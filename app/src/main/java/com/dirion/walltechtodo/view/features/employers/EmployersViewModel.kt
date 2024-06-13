package com.dirion.walltechtodo.view.features.employers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllSimpleUsers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployersViewModel(
    private val useCaseGetAllSimpleUsers: UseCaseGetAllSimpleUsers
) : ViewModel() {

    private val _state = MutableStateFlow<List<USER>>(listOf())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update {
                useCaseGetAllSimpleUsers()
            }
        }
    }


}