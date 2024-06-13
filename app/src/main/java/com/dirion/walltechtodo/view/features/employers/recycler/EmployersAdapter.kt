package com.dirion.walltechtodo.view.features.employers.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator
import kotlinx.coroutines.Deferred

class EmployersAdapter(
) : ListAdapter<USER, EmployersVH>(Comparator.employersDU) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployersVH {
        return EmployersVH(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: EmployersVH, position: Int) {
        holder.bind(getItem(position))
    }
}