package com.dirion.walltechtodo.view.features.add_task

import com.dirion.walltechtodo.view.global.StatusTask


data class AddTaskModel(
    val taskName: String,
    val categories: Map<StatusTask, Boolean>,
)
