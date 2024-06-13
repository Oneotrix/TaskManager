package com.dirion.walltechtodo.view.features.customers.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.data.models.network.rest.CLIENT
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator

class CustomersAdapter : ListAdapter<CLIENT, CustomerVH>(Comparator.customersDU) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerVH {
        return CustomerVH(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomerVH, position: Int) {
        holder.bind(getItem(position))
    }
}