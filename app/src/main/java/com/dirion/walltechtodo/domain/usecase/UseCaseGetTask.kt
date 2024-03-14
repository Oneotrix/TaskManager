package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseGetTask @Inject constructor(
    private val tasksRepository: TasksRepository,
) {
    fun execute() = tasksRepository.getTasks()

    suspend fun fetch() = tasksRepository.fetchTasks()

}