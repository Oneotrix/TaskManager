package com.dirion.walltechtodo.view.features.add_task.recycler

import com.dirion.walltechtodo.view.global.StatusTask

data class StatusVhModel(
    val status: StatusTask,
    val isChecked: Boolean
)
