package com.dirion.walltechtodo.view.ui.tasks.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.view.ui.tasks.TaskModel

class TasksVH(
    val binding: ItemTasksListBinding): ViewHolder(binding.root) {

        fun bind(model: TaskModel) = with(binding){
            tvTaskStatus.text = model.status.status
            tvTaskTitle.text = model.title
            tvTaskId.text = "#${model.id}"
        }
}