package com.dirion.walltechtodo.data.models.network.rest.response

import kotlinx.serialization.Serializable

@Serializable
data class PutUpdateTaskModelResponse(
    val id: Long,
    val title: String,
    val status: String,
)
