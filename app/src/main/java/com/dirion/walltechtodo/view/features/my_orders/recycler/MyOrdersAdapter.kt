package com.dirion.walltechtodo.view.features.my_orders.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator

class MyOrdersAdapter: ListAdapter<ORDER, MyOrdersViewHolder>(Comparator.orderDU) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrdersViewHolder {
        return MyOrdersViewHolder(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyOrdersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}