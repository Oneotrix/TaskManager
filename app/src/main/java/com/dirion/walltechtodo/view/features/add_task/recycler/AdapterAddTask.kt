package com.dirion.walltechtodo.view.features.add_task.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.CarouselItemBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.global.StatusTask


class AdapterAddTask(
    val callback: (StatusTask) -> Unit
): ListAdapter<StatusVhModel, CategoriesVH>(Comparator.addTaskDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        return CategoriesVH(
            binding = CarouselItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.bind(getItem(position), callback)
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int, payloads: MutableList<Any>) {
        when(val latestPayload = payloads.lastOrNull()) {
            is Comparator.AddTaskPayload.Switcher -> holder.bindSwitcher(latestPayload.isChecked)
            else -> onBindViewHolder(holder, position)
        }
    }

}