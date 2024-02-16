package com.dirion.walltechtodo.data.models.network.rest.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseModel(
    val result: Boolean,
)
