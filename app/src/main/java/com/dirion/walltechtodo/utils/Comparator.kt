package com.dirion.walltechtodo.utils

import androidx.recyclerview.widget.DiffUtil
import com.dirion.walltechtodo.view.features.add_task.recycler.StatusVhModel
import com.dirion.walltechtodo.view.features.tasks.TaskModel

object Comparator {

    val tasksListDiffUtil = object: DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel) =
            oldItem.status == newItem.status &&
            oldItem.title == newItem.title &&
            oldItem.id == newItem.id
    }


    val addTaskDiffUtil = object: DiffUtil.ItemCallback<StatusVhModel>() {
        override fun areItemsTheSame(oldItem: StatusVhModel, newItem: StatusVhModel): Boolean {
            return oldItem.status == newItem.status
        }

        override fun areContentsTheSame(oldItem: StatusVhModel, newItem: StatusVhModel): Boolean {
            return oldItem.isChecked == newItem.isChecked
        }


        override fun getChangePayload(oldItem: StatusVhModel, newItem: StatusVhModel): Any? {
            return when(oldItem.isChecked == newItem.isChecked) {
                true -> super.getChangePayload(oldItem, newItem)
                false -> AddTaskPayload.Switcher(newItem.isChecked)
            }
        }
    }


    sealed interface AddTaskPayload {
        data class Switcher(val isChecked: Boolean) : AddTaskPayload
    }
}