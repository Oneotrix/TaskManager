package com.dirion.walltechtodo.view.features.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseAddTask
import javax.inject.Inject

class AddTaskViewModelFactory @Inject constructor(
    private val useCaseAddTask: UseCaseAddTask
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(useCaseAddTask) as T
    }

}