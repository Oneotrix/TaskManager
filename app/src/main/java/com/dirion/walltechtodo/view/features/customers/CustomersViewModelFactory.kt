package com.dirion.walltechtodo.view.features.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllCustomers
import javax.inject.Inject

class CustomersViewModelFactory @Inject constructor(
    private val useCaseGetAllCustomers: UseCaseGetAllCustomers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomersViewModel(
            useCaseGetAllCustomers = useCaseGetAllCustomers
        ) as T
    }
}