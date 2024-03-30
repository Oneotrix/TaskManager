package com.dirion.walltechtodo.view.mapper

import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.view.global.StatusTask
import com.dirion.walltechtodo.view.features.tasks.TaskModel

object MapperUi {

    fun mapTaskModelUi(
        taskModelDomain: TaskModelDomain
    ): TaskModel {

        val status = when(taskModelDomain.status) {
            StatusTask.TO_DO.statusTitle -> StatusTask.DONE
            StatusTask.IN_PROGRESS.statusTitle -> StatusTask.IN_PROGRESS
            StatusTask.UNDER_REVIEW.statusTitle -> StatusTask.UNDER_REVIEW
            StatusTask.TESTING.statusTitle -> StatusTask.DONE
            StatusTask.DONE.statusTitle -> StatusTask.DONE
            else -> error("error Model")
        }

        return TaskModel(
            title = taskModelDomain.title,
            status = status,
            id = taskModelDomain.id,
            showDeleteButton = false
        )
    }

}