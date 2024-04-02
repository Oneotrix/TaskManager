package com.dirion.walltechtodo.view.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllTask
import javax.inject.Inject
@ScopeApplication
class TasksViewModelFactory @Inject constructor(
    private val useCaseGetAllTasks : UseCaseGetAllTask
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(
            useCaseGetAllTask = useCaseGetAllTasks
        ) as T
    }

}