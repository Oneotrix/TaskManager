package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseDeleteTypography @Inject constructor(
    private val orderRepository: IOrdersRepository
) {

    suspend operator fun invoke(id: String) = withContext(Dispatchers.IO) {
        orderRepository.deleteTypography(id)
    }

}