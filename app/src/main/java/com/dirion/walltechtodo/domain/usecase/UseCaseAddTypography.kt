package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseAddTypography @Inject constructor(
    private val orderRepository: IOrdersRepository
) {
    suspend operator fun invoke(model: TIPOGRAPHY) = withContext(Dispatchers.IO) {
        orderRepository.addTypography(
            hood = model.hood,
            name = model.name,
            phone = model.phone,
            typeId = model.type,
            year = model.year
        )
    }
}