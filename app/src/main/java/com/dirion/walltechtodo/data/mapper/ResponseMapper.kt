package com.dirion.walltechtodo.data.mapper

import com.dirion.walltechtodo.data.models.network.rest.response.TaskResponseModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain

object ResponseMapper {

    fun mapTaskModel(
         taskResponseModel: TaskResponseModel
    ): TaskModelDomain {
        return TaskModelDomain(
            title = taskResponseModel.title,
            status = taskResponseModel.status,
            id = taskResponseModel.id
        )
    }

}