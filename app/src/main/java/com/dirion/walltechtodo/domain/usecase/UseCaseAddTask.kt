package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseAddTask @Inject constructor(
    private val tasksRepository: ITasksRepository
) {
    suspend fun addTask(name: String, status: String) = withContext(Dispatchers.IO){
        tasksRepository.addTask(name, status)
    }
}