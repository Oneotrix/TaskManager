package com.dirion.walltechtodo.view.features.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.domain.usecase.UseCaseDeleteOrder
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllOrders
import com.dirion.walltechtodo.view.features.orders.OrdersViewModel.State.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*


class OrdersViewModel(
    private val useCaseGetAllOrders: UseCaseGetAllOrders,
    private val useCaseDeleteOrder: UseCaseDeleteOrder
): ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data: StateFlow<UiState> = _data.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() = viewModelScope.launch {

        useCaseGetAllOrders.fetch().collect { list ->
            val models = list.map {
                OrderModel(
                    id = it.id,
                    acceptance_date = it.acceptance_date,
                    binding_type = it.binding_type,
                    completion_date = it.completion_date,
                    selectCustomer = it.customer,
                    edition = it.edition,
                    selectedEmployer = it.employer,
                    selectedFormat = ORDER.Format.convertToFormat(it.format),
                    pages_count = it.pages_count,
                    paper_type = it.paper_type,
                    prepayment = it.prepayment,
                    printing = it.printing,
                    product_price = it.product_price,
                    product_type = it.product_type,
                    selectedTypography = it.tipography_id,
                )
            }
            _data.value = UiState(orders = models)
        }


    }

    fun deleteOrder(id: String) = viewModelScope.launch {
        changeOrderList(id)

        useCaseDeleteOrder.delete(id)
    }

    private suspend fun changeOrderList(id: String) {
        val newList: MutableList<OrderModel> = mutableListOf()

        _data.value.orders.forEach { model ->
            if (model.id != id) newList.add(model)
        }

        _data.emit(UiState(orders = newList))
    }


    sealed class State {
        data class UiState(val orders: List<OrderModel> = emptyList())
    }
}

