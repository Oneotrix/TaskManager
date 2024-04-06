package com.dirion.walltechtodo.view.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.usecase.UseCaseDeleteTask
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllTask
import com.dirion.walltechtodo.view.features.tasks.TasksViewModel.State.UiState
import com.dirion.walltechtodo.view.mapper.MapperUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*


class TasksViewModel(
    private val useCaseGetAllTask: UseCaseGetAllTask,
    private val useCaseDeleteTask: UseCaseDeleteTask
): ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data: StateFlow<UiState> = _data.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() = viewModelScope.launch {

        useCaseGetAllTask.fetch().collect { list ->
            _data.value = UiState(tasks = list.map { model ->
                MapperUi.mapTaskModelUi(model) })
        }
    }

    fun deleteTask(id: Long) = viewModelScope.launch {
        changeTaskList(id)

        useCaseDeleteTask.delete(id)
    }

    private suspend fun changeTaskList(id: Long) {
        val newList: MutableList<TaskModel> = mutableListOf()

        _data.value.tasks.forEach { model ->
            if (model.id != id) newList.add(model)
        }

        _data.emit(UiState(tasks = newList))
    }


    sealed class State {
        data class UiState(val tasks: List<TaskModel> = emptyList())
    }
}

