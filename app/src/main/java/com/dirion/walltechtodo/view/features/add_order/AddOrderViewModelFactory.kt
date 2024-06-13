package com.dirion.walltechtodo.view.features.add_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseAddOrder
import com.dirion.walltechtodo.domain.usecase.UseCaseFetchAllEmployers
import com.dirion.walltechtodo.domain.usecase.UseCaseFetchAllTypography
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllCustomers
import javax.inject.Inject

class AddOrderViewModelFactory @Inject constructor(
    private val useCaseAddOrder: UseCaseAddOrder,
    private val useCaseFetchAllEmployers: UseCaseFetchAllEmployers,
    private val useCaseFetchAllTypography: UseCaseFetchAllTypography,
    private val useCaseGetAllCustomers: UseCaseGetAllCustomers,
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddOrderViewModel(
            useCaseAddOrder = useCaseAddOrder,
            useCaseFetchAllEmployers = useCaseFetchAllEmployers,
            useCaseFetchAllTypography = useCaseFetchAllTypography,
            useCaseGetAllCustomers = useCaseGetAllCustomers
        ) as T
    }

}