package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
class UseCaseGetTask @Inject constructor(
    private val tasksRepository: ITasksRepository
) {
    suspend fun fetchTask(id: Long) = withContext(Dispatchers.IO) {
        tasksRepository.getTask(id)
    }
}