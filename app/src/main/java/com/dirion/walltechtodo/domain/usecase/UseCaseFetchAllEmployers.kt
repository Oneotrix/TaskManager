package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.data.OrdersRepository
import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseFetchAllEmployers @Inject constructor(
    private val ordersRepository: IOrdersRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        return@withContext ordersRepository.getAllEmployers()
    }
}