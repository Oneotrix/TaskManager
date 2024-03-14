package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.domain.models.TaskModelDomain
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
     fun getTasks() : Flow<List<TaskModelDomain>>
     suspend fun fetchTasks()
}