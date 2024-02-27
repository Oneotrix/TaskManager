package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.ui.tasks.TaskModel

class AdapterTasks: ListAdapter<TaskModel, TasksVH>
    (Comparator.tasksListDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksVH {
        return TasksVH(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TasksVH, position: Int) {
        holder.bind(getItem(position))
    }

}