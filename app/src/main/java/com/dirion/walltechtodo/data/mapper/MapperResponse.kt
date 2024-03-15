package com.dirion.walltechtodo.data.mapper

import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponce
import com.dirion.walltechtodo.domain.models.TaskModelDomain

object MapperResponse {

    fun mapTaskModel(
        taskModelResponse: GetTaskModelResponce
    ): TaskModelDomain {
        return TaskModelDomain(
            title = taskModelResponse.title,
            status = taskModelResponse.status,
            id = taskModelResponse.id
        )
    }

}