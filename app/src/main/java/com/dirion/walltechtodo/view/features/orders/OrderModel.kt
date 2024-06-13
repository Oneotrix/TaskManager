package com.dirion.walltechtodo.view.features.orders

import com.dirion.walltechtodo.data.models.network.rest.ORDER

data class OrderModel(
    val id: String? = null,
    val acceptance_date: String = "",
    val binding_type: String = "",
    val completion_date: String = "",
    val selectCustomer: String = "",
    val customersList: List<String> = listOf(),
    val edition: String = "",
    val selectedEmployer: String? = null,
    val employers: List<String> = listOf(),
    val selectedFormat: ORDER.Format = ORDER.Format.a,
    val pages_count: Int = 0,
    val paper_type: String = "",
    val prepayment: Boolean = false,
    val printing: Int = 0,
    val product_price: Int = 0,
    val product_type: String = "",
    val selectedTypography: String = "",
    val typographyList: List<String> = listOf(),
    var showDeleteButton: Boolean = false,
)
