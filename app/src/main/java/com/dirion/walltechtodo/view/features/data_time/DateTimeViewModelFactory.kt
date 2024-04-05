package com.dirion.walltechtodo.view.features.data_time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithDate
import javax.inject.Inject

class DateTimeViewModelFactory @Inject constructor(
    private val useCaseWorkWithDate: UseCaseWorkWithDate
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DateTimeViewModel(useCaseWorkWithDate) as T
    }

}