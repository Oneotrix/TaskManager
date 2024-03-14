package com.dirion.walltechtodo.view.ui.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import com.dirion.walltechtodo.view.mapper.DomainMapper
import com.dirion.walltechtodo.view.ui.tasks.State.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class TasksViewModel(
    private val useCaseGetTask: UseCaseGetTask
): ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data: StateFlow<UiState> = _data.asStateFlow()

    init {
        Log.d("TasksViewModel","Init")
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val res = useCaseGetTask.execute()
           // val uiState = mutableListOf<UiState>()
            res.collect { element ->
                _data.getAndUpdate {tasks ->
                    val newList = mutableListOf<TaskModel>()
                    newList.addAll(DomainMapper.mapListTaskModelUi(element))
                    tasks.copy(tasks = newList)
                }
            }
            _data.getAndUpdate { tasks ->
                tasks.copy(tasks = DomainMapper.mapListTaskModelUi(emptyList()))
            }
            Log.d("TasksViewModel", "res : ${res}" )
            Log.d("TasksViewModel", "_data : ${_data.value}" )
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    fun changeTasksList(tasks: List<TaskModel>) {
        viewModelScope.launch {
            _data.emit(UiState(tasks))
        }
    }

}