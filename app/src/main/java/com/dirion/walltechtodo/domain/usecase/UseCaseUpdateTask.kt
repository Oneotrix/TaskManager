package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCaseUpdateTask @Inject constructor(
    private val tasksRepository: IOrdersRepository
) {
    suspend fun updateTask(
        order: ORDER
    ) = withContext(Dispatchers.IO) {
        tasksRepository.updateOrder(
            id = order.id,
            acceptance_date   = order.acceptance_date,
            binding_type      = order.binding_type,
            completion_date   = order.completion_date,
            customerPassport  = order.customer,
            edition           = order.edition,
            employerLogin     = order.employer,
            format            = ORDER.Format.convertToFormat(order.format),
            pages_count       = order.pages_count,
            paper_type        = order.paper_type,
            prepayment        = order.prepayment,
            printing          = order.printing,
            product_price     = order.product_price,
            product_type      = order.product_type,
            tipographyName    = order.tipography_id,
        )
    }
}