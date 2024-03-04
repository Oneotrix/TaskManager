package com.dirion.walltechtodo.utils

import com.dirion.walltechtodo.view.global.StatusTask
import com.dirion.walltechtodo.view.ui.tasks.TaskModel

object TestData {

    val taskList = listOf<TaskModel>(
        TaskModel(
            title = "Task1",
            status = StatusTask.TO_DO,
            id = 1,
            showDeleteButton = false
        ),
        TaskModel(
            title = "Task2",
            status = StatusTask.TO_DO,
            id = 2,
            showDeleteButton = false
        ),
        TaskModel(
            title = "Task3",
            status = StatusTask.TESTING,
            id = 3,
            showDeleteButton = false
        ),
        TaskModel(
            title = "Task4",
            status = StatusTask.IN_PROGRESS,
            id = 4,
            showDeleteButton = false
        ),
        TaskModel(
            title = "Task5",
            status = StatusTask.DONE,
            id = 5,
            showDeleteButton = false
        ),TaskModel(
            title = "Task6",
            status = StatusTask.UNDER_REVIEW,
            id = 6,
            showDeleteButton = false
        ),TaskModel(
            title = "Task7",
            status = StatusTask.IN_PROGRESS,
            id = 7,
            showDeleteButton = false
        ),TaskModel(
            title = "Task8",
            status = StatusTask.TO_DO,
            id = 8,
            showDeleteButton = false
        ),TaskModel(
            title = "Task9",
            status = StatusTask.TO_DO,
            id = 9,
            showDeleteButton = false
        ),TaskModel(
            title = "Task10",
            status = StatusTask.UNDER_REVIEW,
            id = 10,
            showDeleteButton = false
        ),TaskModel(
            title = "Task11",
            status = StatusTask.DONE,
            id = 11,
            showDeleteButton = false
        ),TaskModel(
            title = "Task12",
            status = StatusTask.TESTING,
            id = 12,
            showDeleteButton = false
        ),TaskModel(
            title = "Task13",
            status = StatusTask.TESTING,
            id = 13,
            showDeleteButton = false
        ),

    )

}