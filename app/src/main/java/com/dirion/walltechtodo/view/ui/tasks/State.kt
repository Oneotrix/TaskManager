package com.dirion.walltechtodo.view.ui.tasks

sealed class State {
    data class UiState(val tasks: List<TaskModel> = emptyList())


}
