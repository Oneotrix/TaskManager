package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ScopeApplication
class UseCaseGetAllTask @Inject constructor(
    private val tasksRepository: ITasksRepository,
) {

     suspend fun fetch() = withContext(Dispatchers.IO) {
         return@withContext tasksRepository.fetchTasks()
    }

}