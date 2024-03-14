package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.TasksRepository
import javax.inject.Inject

class UseCaseGetTask @Inject constructor(
    private val tasksRepository: TasksRepository,
) {
     suspend fun execute() = tasksRepository.getTasks()

}