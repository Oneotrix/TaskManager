package com.dirion.walltechtodo.data.models.network.rest.request.delete

import kotlinx.serialization.Serializable

@Serializable
data class DeleteTaskModelRequest(
    val id: Long,
)