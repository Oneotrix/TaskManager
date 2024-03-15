package com.dirion.walltechtodo.data.models.network.rest.request.post

import kotlinx.serialization.Serializable

@Serializable
data class PostAddTaskModelRequest(
    val status: String,
    val title: String,
)
