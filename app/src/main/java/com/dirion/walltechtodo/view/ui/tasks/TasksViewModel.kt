package com.dirion.walltechtodo.view.ui.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import com.dirion.walltechtodo.view.mapper.DomainMapper
import com.dirion.walltechtodo.view.ui.tasks.State.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class TasksViewModel(
    private val useCaseGetTask: UseCaseGetTask
): ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data: StateFlow<UiState> = _data.asStateFlow()

    init {
        Log.d("TasksViewModel","Init")
        collectData()
        fetchData()
    }

    private fun collectData() = viewModelScope.launch {
        useCaseGetTask.execute()
            .collect { element ->
                _data.getAndUpdate { tasks ->
                    tasks.copy(tasks = element.let(DomainMapper::mapListTaskModelUi))
                }
            }
    }

    private fun fetchData() = viewModelScope.launch {
        useCaseGetTask.fetch()
    }

    fun changeTasksList(tasks: List<TaskModel>) {
        viewModelScope.launch {
            _data.emit(UiState(tasks))
        }
    }

}