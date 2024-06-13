package com.dirion.walltechtodo.view.features.my_orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.domain.usecase.UseCaseGetMyOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyOrdersViewModel(
    private val useCaseGetMyOrders: UseCaseGetMyOrders
) : ViewModel() {

    private val _state = MutableStateFlow<List<ORDER>>(listOf())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update {
                useCaseGetMyOrders()
            }
        }
    }

}