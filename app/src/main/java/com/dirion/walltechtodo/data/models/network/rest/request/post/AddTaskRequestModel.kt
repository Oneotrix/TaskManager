package com.dirion.walltechtodo.data.models.network.rest.request.post

import kotlinx.serialization.Serializable

@Serializable
data class AddTaskRequestModel(
    val status: String,
    val title: String,
)
