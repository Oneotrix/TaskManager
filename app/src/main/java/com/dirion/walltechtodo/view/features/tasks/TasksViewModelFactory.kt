package com.dirion.walltechtodo.view.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import javax.inject.Inject
@ScopeApplication
class TasksViewModelFactory @Inject constructor(
    private val useCaseGetTasks : UseCaseGetTask
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(
            useCaseGetTask = useCaseGetTasks
        ) as T
    }

}