package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ScopeApplication
class UseCaseGetTask @Inject constructor(
    private val ITasksRepository: ITasksRepository,
) {

     suspend fun fetch() = withContext(Dispatchers.IO) {
         return@withContext ITasksRepository.fetchTasks()
    }

}