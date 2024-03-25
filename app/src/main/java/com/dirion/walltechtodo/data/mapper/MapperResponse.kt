package com.dirion.walltechtodo.data.mapper

import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponse
import com.dirion.walltechtodo.domain.models.TaskModelDomain

object MapperResponse {

    fun mapTaskModel(
        taskModelResponse: GetTaskModelResponse
    ): TaskModelDomain {
        return TaskModelDomain(
            title = taskModelResponse.title,
            status = taskModelResponse.status,
            id = taskModelResponse.id
        )
    }

}