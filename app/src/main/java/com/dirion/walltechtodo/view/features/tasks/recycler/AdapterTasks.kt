package com.dirion.walltechtodo.view.features.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.features.tasks.TaskModel
import com.dirion.walltechtodo.view.features.tasks.recycler.gesture.ItemDeleteListener


class AdapterTasks(
    private val callbackDeleteTask: (List<TaskModel>) -> Unit,
    private val callbackEditTask: (Long) -> Unit
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

        val listener = object : ItemDeleteListener {
            override fun delete(position: Int) {
                val newList = mutableListOf<TaskModel>()
                newList.addAll(this@AdapterTasks.currentList)
                newList.removeAt(position)

                this@AdapterTasks.submitList(newList)
                this@AdapterTasks.notifyDataSetChanged()

                callbackDeleteTask.invoke(newList)
            }
        }

        holder.bind(getItem(position), listener)
    }


}