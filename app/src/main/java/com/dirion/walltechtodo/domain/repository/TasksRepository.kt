package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.domain.models.TaskModelDomain

interface TasksRepository {
     suspend fun fetchTasks() : List<TaskModelDomain>
}