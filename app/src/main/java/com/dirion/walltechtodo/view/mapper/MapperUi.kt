package com.dirion.walltechtodo.view.mapper

import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.view.global.StatusTask
import com.dirion.walltechtodo.view.features.tasks.TaskModel

object MapperUi {

    fun mapTaskModelUi(
        taskModelDomain: TaskModelDomain
    ): TaskModel {

        val status = StatusTask.convertToStatus(taskModelDomain.status)

        return TaskModel(
            title = taskModelDomain.title,
            status = status,
            id = taskModelDomain.id,
            showDeleteButton = false
        )
    }

}