package com.dirion.walltechtodo.view.features.edit_order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.domain.usecase.UseCaseFetchAllEmployers
import com.dirion.walltechtodo.domain.usecase.UseCaseFetchAllTypography
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllCustomers
import com.dirion.walltechtodo.domain.usecase.UseCaseGetOrder
import com.dirion.walltechtodo.domain.usecase.UseCaseUpdateTask
import com.dirion.walltechtodo.view.features.orders.OrderModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditTaskViewModel(
    private val useCaseGetOrder: UseCaseGetOrder,
    private val useCaseUpdateTask: UseCaseUpdateTask,
    private val useCaseFetchAllEmployers: UseCaseFetchAllEmployers,
    private val useCaseFetchAllTypography: UseCaseFetchAllTypography,
    private val useCaseGetAllCustomers: UseCaseGetAllCustomers,
) : ViewModel() {

    private val _data = MutableStateFlow(UiState.State())
    val data = _data.asStateFlow()
    private val employersLogin = mutableListOf<Pair<String, String>>()
    var id: String = ""

    fun fetchTask(orderId: String) = viewModelScope.launch {
        _data.update {
            val model = useCaseGetOrder.fetchTask(orderId)
            id = model.id
            it.copy(
                model = OrderModel(
                    id = model.id,
                    acceptance_date = model.acceptance_date,
                    binding_type = model.binding_type,
                    completion_date = model.completion_date,
                    selectCustomer = model.customer,
                    edition = model.edition,
                    selectedEmployer = model.employer,
                    selectedFormat = ORDER.Format.convertToFormat(model.format),
                    pages_count = model.pages_count,
                    paper_type = model.paper_type,
                    prepayment = model.prepayment,
                    printing = model.printing,
                    product_price = model.product_price,
                    product_type = model.product_type,
                    selectedTypography = model.tipography_id,
                    employers = useCaseFetchAllEmployers()
                        .also {
                            it.forEach {
                                employersLogin.add(it)
                            }
                        }
                        .map { it.second },
                    typographyList = useCaseFetchAllTypography(),
                    customersList = useCaseGetAllCustomers()
                )
            )
        }
    }


    fun updateTask() = viewModelScope.launch {
        if (
            _data.value.model.acceptance_date.isNotBlank() &&
            _data.value.model.completion_date.isNotBlank() &&
            _data.value.model.selectCustomer.isNotBlank() &&
            _data.value.model.edition.isNotBlank() &&
            _data.value.model.pages_count > 0 &&
            _data.value.model.paper_type.isNotBlank() &&
            _data.value.model.printing > 0 &&
            _data.value.model.product_price > 0 &&
            _data.value.model.product_type.isNotBlank()
        ) {

            val employer = employersLogin.filter {
                it.second == _data.value.model.selectedEmployer
            }
                .first()
                .first

            useCaseUpdateTask.updateTask(ORDER (
                id = id,
                acceptance_date = _data.value.model.acceptance_date,
                binding_type = _data.value.model.binding_type,
                completion_date = _data.value.model.completion_date,
                customer = _data.value.model.selectCustomer,
                edition = _data.value.model.edition,
                employer = employer,
                format = ORDER.Format.convertToString(_data.value.model.selectedFormat!!),
                pages_count = _data.value.model.pages_count,
                paper_type = _data.value.model.paper_type,
                prepayment = _data.value.model.prepayment,
                printing = _data.value.model.printing,
                product_price = _data.value.model.product_price,
                product_type = _data.value.model.product_type,
                tipography_id = _data.value.model.selectedTypography
            ))
        }

    }

    fun updateAcceptance_date(acceptanceDate: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    acceptance_date = acceptanceDate
                )
            )
        }
    }

    fun updateBinding_type(binding_type: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    binding_type = binding_type
                )
            )
        }
    }

    fun updateCompletion_date(completion_date: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    completion_date = completion_date
                )
            )
        }
    }

    fun updateCustomer(customer: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    selectCustomer = customer
                )
            )
        }
    }

    fun updateEdition(edition: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    edition = edition
                )
            )
        }
    }

    fun updateEmployer(employer: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    selectedEmployer = employer
                )
            )
        }
    }

    fun updateFormat(format: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    selectedFormat = ORDER.Format.convertToFormat(format)
                )
            )
        }
        Log.d("updateFormat", "${_data.value.model.selectedFormat}")

    }

    fun updatePagesCount(pages_count: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    pages_count = pages_count.toIntOrNull() ?: 0
                )
            )
        }
    }

    fun updatePaperType(paper_type: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    paper_type = paper_type
                )
            )
        }
    }

    fun updatePrepayment(prepayment: String) {

        val answer = when(prepayment.lowercase()) {
            "да" -> true
            else -> false
        }
        _data.update {

            EditTaskViewModel.UiState.State(
                it.model.copy(
                    prepayment = answer
                )
            )

        }
    }

    fun updatePrinting(printing: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    printing = printing.toIntOrNull() ?: 0
                )
            )
        }
    }

    fun updateProductPrice(product_price: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    product_price = product_price.toIntOrNull() ?: 0
                )
            )
        }
    }

    fun updateProductType(product_type: String) {
        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    product_type = product_type
                )
            )
        }
    }

    fun updateTipographyId(tipography_id: String) {

        _data.update {
            EditTaskViewModel.UiState.State(
                it.model.copy(
                    selectedTypography = tipography_id
                )
            )
        }
    }

    sealed class UiState() {
        data class State(val model: OrderModel = OrderModel()): EditTaskViewModel.UiState()
    }
}