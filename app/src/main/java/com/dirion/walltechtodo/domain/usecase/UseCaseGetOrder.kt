package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
class UseCaseGetOrder @Inject constructor(
    private val tasksRepository: IOrdersRepository
) {
    suspend fun fetchTask(id: String) = withContext(Dispatchers.IO) {
        tasksRepository.getOrder(id)
    }
}