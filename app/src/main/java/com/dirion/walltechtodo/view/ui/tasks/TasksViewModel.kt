package com.dirion.walltechtodo.view.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewModel(
    useCaseGetTask: UseCaseGetTask
): ViewModel() {

    private val _data = MutableStateFlow(useCaseGetTask.execute())
    val data: StateFlow<List<TaskModel>> = _data.asStateFlow()


    fun changeData(data: List<TaskModel>) {
        viewModelScope.launch {
            _data.emit(data)
        }
    }


}