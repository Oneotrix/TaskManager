package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.domain.models.TaskModelDomain
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
     suspend fun getTasks() : Flow<List<TaskModelDomain>>
}