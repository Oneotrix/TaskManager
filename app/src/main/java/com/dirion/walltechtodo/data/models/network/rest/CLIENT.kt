package com.dirion.walltechtodo.data.models.network.rest

data class CLIENT(
    val name: String,
    val middlename: String,
    val passport: String,
    val surname: String,
    val account: String,
    val bank: String,
    val birthdate: Int,
    val phone: String,
    val address: String,
)
