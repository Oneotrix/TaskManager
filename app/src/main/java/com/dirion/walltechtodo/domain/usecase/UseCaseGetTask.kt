package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.di.qualifier.TasksRepositoryImpl
import com.dirion.walltechtodo.domain.repository.TasksRepository
import javax.inject.Inject

class UseCaseGetTask @Inject constructor(
   @TasksRepositoryImpl
   private val tasksRepository: TasksRepository
) {
    fun execute() = tasksRepository.getTasks()

}