package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ScopeApplication
class UseCaseGetTask @Inject constructor(
    private val tasksRepository: ITasksRepository
) {
    suspend fun fetchTask(id: Long) = withContext(Dispatchers.IO) {
        tasksRepository.getTask(id)
    }
}