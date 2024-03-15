package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseGetTask @Inject constructor(
    private val tasksRepository: TasksRepository,
) {

    suspend fun fetch() = withContext(Dispatchers.IO) {
         tasksRepository.fetchTasks()
    }

}