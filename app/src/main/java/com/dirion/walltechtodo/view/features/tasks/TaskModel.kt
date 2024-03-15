package com.dirion.walltechtodo.view.features.tasks

import com.dirion.walltechtodo.view.global.StatusTask

data class TaskModel(
    val title: String,
    val status: StatusTask,
    val id: Long,
    var showDeleteButton: Boolean
)
