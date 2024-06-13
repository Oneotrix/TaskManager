package com.dirion.walltechtodo.view.features.edit_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseFetchAllEmployers
import com.dirion.walltechtodo.domain.usecase.UseCaseFetchAllTypography
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllCustomers
import com.dirion.walltechtodo.domain.usecase.UseCaseGetOrder
import com.dirion.walltechtodo.domain.usecase.UseCaseUpdateTask
import javax.inject.Inject

class EditTaskViewModelFactory @Inject constructor(
    private val useCaseGetOrder: UseCaseGetOrder,
    private val useCaseUpdateTask: UseCaseUpdateTask,
    private val useCaseFetchAllEmployers: UseCaseFetchAllEmployers,
    private val useCaseFetchAllTypography: UseCaseFetchAllTypography,
    private val useCaseGetAllCustomers: UseCaseGetAllCustomers,

    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditTaskViewModel(
            useCaseGetOrder = useCaseGetOrder,
            useCaseUpdateTask = useCaseUpdateTask,
            useCaseFetchAllEmployers = useCaseFetchAllEmployers,
            useCaseFetchAllTypography = useCaseFetchAllTypography,
            useCaseGetAllCustomers = useCaseGetAllCustomers
        ) as T
    }

}