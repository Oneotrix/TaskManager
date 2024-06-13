package com.dirion.walltechtodo.view.features.employers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllSimpleUsers
import javax.inject.Inject

class EmployersViewModelFactory @Inject constructor(
    private val useCaseGetAllSimpleUsers: UseCaseGetAllSimpleUsers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployersViewModel(
            useCaseGetAllSimpleUsers = useCaseGetAllSimpleUsers
        ) as T
    }
}