package com.dirion.walltechtodo.view.features.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.CLIENT
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllCustomers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomersViewModel(
    private val useCaseGetAllCustomers: UseCaseGetAllCustomers
): ViewModel() {

    private val _state = MutableStateFlow(UiState.Customers())
     val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    list = useCaseGetAllCustomers.getCustomersList()
                )
            }
        }
    }


    sealed class UiState() {

        data class Customers(
            val list: List<CLIENT> = listOf()
        ) : UiState()

    }
}