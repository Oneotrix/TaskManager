package com.dirion.walltechtodo.utils

import androidx.recyclerview.widget.DiffUtil
import com.dirion.walltechtodo.view.ui.tasks.TaskModel

object Comparator {

    val tasksListDiffUtil = object: DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.status == newItem.status && oldItem.title == newItem.title
        }

    }

}