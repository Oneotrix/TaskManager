package com.dirion.walltechtodo.view.features.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.usecase.UseCaseAddTask
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.global.StatusTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val useCaseAddTask: UseCaseAddTask) : ViewModel() {

    private val _data = MutableStateFlow(UiState.SwitchersState())
    val data = _data.asStateFlow()


    fun updateStatusTask(statusTask: StatusTask) = viewModelScope.launch {
        val currentStatusTaskValue = _data.value.statusTask.categories[statusTask]!!
        val categories = StatusTask.entries
            .associateWith { false }
            .toMutableMap()

        categories[statusTask] = currentStatusTaskValue.not()

        val newState = UiState.SwitchersState(
            statusTask = AddTaskModel(
                taskName = _data.value.statusTask.taskName,
                categories = categories
            )
        )

        _data.emit(newState)
    }

    fun addTask(taskTitle: String) = viewModelScope.launch{
        var status = ""

        _data.value.statusTask.categories.forEach {
            if (it.value) {
                status = it.key.statusTitle
            }
        }

        useCaseAddTask.addTask(
            name = taskTitle,
            status = status
        )
    }


    sealed class UiState {
        data class SwitchersState(val statusTask: AddTaskModel = TestData.addTaskModel): UiState()
        class ErrorSwitcherState() : UiState()
        class ErrorTextState() : UiState()

    }

}