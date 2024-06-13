package com.dirion.walltechtodo.view.features.orders.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.features.orders.OrderModel


class AdapterOrders(
    private val callbackDeleteTask: (String) -> Unit,
    private val callbackEditTask: (String) -> Unit,
): ListAdapter<OrderModel, OrdersVH>
    (Comparator.ordersListDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersVH {
        return OrdersVH(
            binding = ItemTasksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callbackEditTask = callbackEditTask
        )
    }

    override fun onBindViewHolder(holder: OrdersVH, position: Int) {
        holder.bind(getItem(position), callbackDeleteTask)
    }


}