package com.dirion.walltechtodo.data.models.network.rest.request.post

import kotlinx.serialization.Serializable

@Serializable
data class PostLoginModelRequest(
    val password: String,
    val username: String,
)