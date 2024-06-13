package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseGetAllTypography @Inject constructor(
    private val orderRepository: IOrdersRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        orderRepository.fetchAllTypographys()
    }
}