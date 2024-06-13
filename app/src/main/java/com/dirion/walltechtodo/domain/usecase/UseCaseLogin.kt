package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
class UseCaseLogin @Inject constructor(
    private val tasksRepository: IOrdersRepository
) {
    suspend fun login(username: String, password: String) = withContext(Dispatchers.IO) {
        return@withContext tasksRepository.login(username, password)
    }
}