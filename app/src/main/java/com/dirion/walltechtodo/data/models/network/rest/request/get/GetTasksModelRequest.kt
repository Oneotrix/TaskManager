package com.dirion.walltechtodo.data.models.network.rest.request.get

import kotlinx.serialization.Serializable

@Serializable
data class GetTasksModelRequest(
    val id: Long,
)
