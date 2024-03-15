package com.dirion.walltechtodo.data.models.network.rest.request.put

import kotlinx.serialization.Serializable

@Serializable
data class PutUpdateTaskModelRequest(
    val status: String,
    val id: Long,
    val title: String,
)
