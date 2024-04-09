package com.dirion.walltechtodo.view.features.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.features.tasks.TaskModel


class AdapterTasks(
    private val callbackDeleteTask: (Long) -> Unit,
    private val callbackEditTask: (Long) -> Unit,
): ListAdapter<TaskModel, TasksVH>
    (Comparator.tasksListDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksVH {
        return TasksVH(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callbackEditTask = callbackEditTask
        )
    }

    override fun onBindViewHolder(holder: TasksVH, position: Int) {
        holder.bind(getItem(position), callbackDeleteTask)
    }


}