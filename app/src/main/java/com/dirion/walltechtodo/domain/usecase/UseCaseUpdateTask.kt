package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseUpdateTask @Inject constructor(
    private val tasksRepository: ITasksRepository
) {
    suspend fun updateTask(id: Long, name: String, status: String) = withContext(Dispatchers.IO) {
        tasksRepository.updateTask(id, name, status)
    }
}