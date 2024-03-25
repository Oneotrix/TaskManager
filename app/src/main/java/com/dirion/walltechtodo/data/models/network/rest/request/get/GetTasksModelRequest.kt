package com.dirion.walltechtodo.data.models.network.rest.request.get

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTasksModelRequest(
    @SerialName("id")
    val id: Long,
)
