package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
class UseCaseGetAllOrders @Inject constructor(
    private val tasksRepository: IOrdersRepository,
) {

     suspend fun fetch() = withContext(Dispatchers.IO) {
         return@withContext tasksRepository.fetchOrders()
    }

}