package com.dirion.walltechtodo.view.features.edit_task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import com.dirion.walltechtodo.domain.usecase.UseCaseUpdateTask
import com.dirion.walltechtodo.view.global.StatusTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditTaskViewModel(
    private val useCaseGetTask: UseCaseGetTask,
    private val useCaseUpdateTask: UseCaseUpdateTask
) : ViewModel() {

    private val _taskState = MutableStateFlow(UiState.EditTaskModel())
    private val _statusMapState = MutableStateFlow(UiState.StatusMap())
    val taskState = _taskState.asStateFlow()
    val statusMapState = _statusMapState.asStateFlow()

    fun fetchTask(id: Long) = viewModelScope.launch {
        val model = useCaseGetTask.fetchTask(id)
        when(model) {
            is BaseDomainModel.Success -> {
                val name = model.data.title
                val status = StatusTask.convertToStatus(model.data.status)
                updateTaskName(name)
                updateStatus(status)
                updateTaskId(id)
            }
            is BaseDomainModel.Error -> {
                Log.d("EditTaskViewModel", model.message)
            }
        }
    }

    private fun updateTaskId(id: Long) = viewModelScope.launch {
        _taskState.emit(_taskState.value.copy(id = id))
    }

    fun editTask() = viewModelScope.launch {
        val taskName = _taskState.value.name
        val taskStatus = _taskState.value.status
        val taskId = _taskState.value.id
        useCaseUpdateTask.updateTask(
            id = taskId,
            name = taskName,
            status = taskStatus.statusTitle
        )
    }

    fun updateStatus(statusTask: StatusTask) = viewModelScope.launch {
        updateStatusMap(statusTask)

        when(_statusMapState.value.map.containsValue(true)) {
            true -> {
                _taskState.emit(_taskState.value.copy(status = statusTask))
            }

            false -> {
                _taskState.emit(_taskState.value.copy(status = StatusTask.TO_DO))
            }
        }

    }

    fun updateTaskName(nameTask: String) = viewModelScope.launch {
        _taskState.emit(_taskState.value.copy(name = nameTask))
    }

    private fun updateStatusMap(status: StatusTask) = viewModelScope.launch {
        val statusValue = _statusMapState.value.map[status]!!
        val statusMap = StatusTask.entries
            .associateWith { false }
            .toMutableMap()

        statusMap[status] = statusValue.not()

        _statusMapState.emit(_statusMapState.value.copy(map = statusMap))
    }

    sealed class UiState() {
        data class EditTaskModel(
            val id: Long = 0,
            val name: String = "",
            val status: StatusTask = StatusTask.TO_DO
        ) : UiState()

        data class StatusMap(val map: Map<StatusTask, Boolean> = StatusTask.entries.associateWith { false })
    }
}