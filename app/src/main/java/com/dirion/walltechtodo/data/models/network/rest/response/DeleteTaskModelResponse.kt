package com.dirion.walltechtodo.data.models.network.rest.response

import kotlinx.serialization.Serializable

@Serializable
data class DeleteTaskModelResponse(
    val result: Boolean,
)
