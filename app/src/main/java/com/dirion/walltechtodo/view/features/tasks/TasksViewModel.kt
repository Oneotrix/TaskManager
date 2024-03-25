package com.dirion.walltechtodo.view.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.domain.models.BaseDomainModel.*
import com.dirion.walltechtodo.domain.usecase.UseCaseGetTask
import com.dirion.walltechtodo.view.features.tasks.TasksViewModel.State.UiState
import com.dirion.walltechtodo.view.mapper.MapperUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewModel(
    private val useCaseGetTask: UseCaseGetTask
): ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data: StateFlow<UiState> = _data.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        val data = useCaseGetTask.fetch()

        when (data){
            is Success -> {
                val tasks = data.data
                    ?.map { model ->
                        MapperUi.mapTaskModelUi(model)
                    }
                    ?: emptyList()

                _data.value = UiState(tasks = tasks)
            }

            else -> {
                /*TODO change to show bar with error */
                _data.value = UiState(tasks = emptyList())
            }
        }


    }


    fun changeTasksList(tasks: List<TaskModel>) = viewModelScope.launch{
        _data.emit(UiState(tasks))
    }


    sealed class State {
        data class UiState(val tasks: List<TaskModel> = emptyList())
    }
}

