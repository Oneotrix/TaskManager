package com.dirion.walltechtodo.view.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseDeleteTask
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllTask
import javax.inject.Inject
class TasksViewModelFactory @Inject constructor(
    private val useCaseGetAllTasks : UseCaseGetAllTask,
    private val useCaseDeleteTask: UseCaseDeleteTask
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(
            useCaseGetAllTasks,
            useCaseDeleteTask
        ) as T
    }

}