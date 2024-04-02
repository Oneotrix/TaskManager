package com.dirion.walltechtodo.view.features.edit_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import com.dirion.walltechtodo.domain.usecase.UseCaseUpdateTask
import javax.inject.Inject

class EditTaskViewModelFactory @Inject constructor(
    private val useCaseGetTask: UseCaseGetTask,
    private val useCaseUpdateTask: UseCaseUpdateTask,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditTaskViewModel(
            useCaseGetTask = useCaseGetTask,
            useCaseUpdateTask = useCaseUpdateTask
        ) as T
    }

}