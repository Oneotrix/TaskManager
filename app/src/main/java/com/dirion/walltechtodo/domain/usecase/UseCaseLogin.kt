package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ScopeApplication
class UseCaseLogin @Inject constructor(
    private val tasksRepository: ITasksRepository
) {
    suspend fun login(username: String, password: String) = withContext(Dispatchers.IO) {
        return@withContext tasksRepository.login(username, password)
    }
}