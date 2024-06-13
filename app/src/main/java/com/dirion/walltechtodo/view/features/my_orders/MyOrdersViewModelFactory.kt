package com.dirion.walltechtodo.view.features.my_orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseGetMyOrders
import javax.inject.Inject

class MyOrdersViewModelFactory @Inject constructor(
    private val useCaseGetMyOrders: UseCaseGetMyOrders
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyOrdersViewModel(
            useCaseGetMyOrders = useCaseGetMyOrders
        ) as T
    }

}