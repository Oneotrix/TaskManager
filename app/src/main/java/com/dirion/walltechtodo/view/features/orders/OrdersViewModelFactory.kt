package com.dirion.walltechtodo.view.features.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseDeleteOrder
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllOrders
import javax.inject.Inject
class OrdersViewModelFactory @Inject constructor(
    private val useCaseGetAllOrders : UseCaseGetAllOrders,
    private val useCaseDeleteOrder: UseCaseDeleteOrder
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(
            useCaseGetAllOrders,
            useCaseDeleteOrder
        ) as T
    }

}