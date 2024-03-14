package com.dirion.walltechtodo.view.mapper

import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.view.global.StatusTask
import com.dirion.walltechtodo.view.ui.tasks.TaskModel

object DomainMapper {

    fun mapListTaskModelUi(
        models: List<TaskModelDomain>
    ): List<TaskModel> {
        val list = mutableListOf<TaskModel>()

        models.forEach {
            list.add(mapTaskModelUi(it))
        }

        return list

    }

    private fun mapTaskModelUi(
        taskModelDomain: TaskModelDomain
    ): TaskModel {

        val status = when(taskModelDomain.status) {
            StatusTask.TO_DO.status -> StatusTask.DONE
            StatusTask.IN_PROGRESS.status -> StatusTask.IN_PROGRESS
            StatusTask.UNDER_REVIEW.status -> StatusTask.UNDER_REVIEW
            StatusTask.TESTING.status -> StatusTask.DONE
            StatusTask.DONE.status -> StatusTask.DONE
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