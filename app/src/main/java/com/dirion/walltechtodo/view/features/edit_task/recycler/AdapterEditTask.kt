package com.dirion.walltechtodo.view.features.edit_task.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemCarouselBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.global.StatusTask


class AdapterEditTask(
    val onUpdateStatus: (StatusTask) -> Unit
): ListAdapter<StatusVhModel, CategoriesVH>(Comparator.updateTaskDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        return CategoriesVH(
            binding = ItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        holder.bind(getItem(position), onUpdateStatus)
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int, payloads: MutableList<Any>) {
        when(val latestPayload = payloads.lastOrNull()) {
            is Comparator.AddTaskPayload.Switcher -> holder.bindSwitcher(latestPayload.isChecked)
            else -> onBindViewHolder(holder, position)
        }
    }

}