package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseDeleteOrder @Inject constructor(
    private val tasksRepository: IOrdersRepository
) {

    suspend fun delete(id: String) = withContext(Dispatchers.IO) {
        tasksRepository.deleteOrder(id)
    }

}