package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.ui.tasks.TaskModel
import com.dirion.walltechtodo.view.ui.tasks.TasksViewModel
import com.dirion.walltechtodo.view.ui.tasks.recycler.gesture.ItemDeleteListener


class AdapterTasks(
    private val callback: (List<TaskModel>) -> Unit
): ListAdapter<TaskModel, TasksVH>
    (Comparator.tasksListDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksVH {
        return TasksVH(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
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

                callback.invoke(newList)
            }
        }

        holder.bind(getItem(position), listener)
    }


}