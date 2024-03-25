package com.dirion.walltechtodo.data.models.network.rest.response

import kotlinx.serialization.Serializable

@Serializable
data class PostAddTaskModelResponse(
    val id: Long,
    val title: String,
    val status: String,
)