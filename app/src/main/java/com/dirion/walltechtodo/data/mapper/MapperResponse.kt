package com.dirion.walltechtodo.data.mapper

import com.dirion.walltechtodo.data.datasource.localSource.room.Task
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

    fun mapToRoomTask(
        taskModelResponse: GetTaskModelResponse
    ): Task{
       return Task(
           id = taskModelResponse.id,
           title = taskModelResponse.title,
           status = taskModelResponse.status,
       )
    }

    fun mapToDomain(task: Task): TaskModelDomain {
        return TaskModelDomain(
            id = task.id,
            title = task.title,
            status = task.status,
        )
    }

}