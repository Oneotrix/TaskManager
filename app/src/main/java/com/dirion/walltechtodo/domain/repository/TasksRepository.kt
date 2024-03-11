package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.view.ui.tasks.TaskModel

interface TasksRepository {
    fun getTasks() : List<TaskModel>
}