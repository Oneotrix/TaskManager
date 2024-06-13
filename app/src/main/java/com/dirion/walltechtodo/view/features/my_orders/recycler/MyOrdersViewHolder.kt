package com.dirion.walltechtodo.view.features.my_orders.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.databinding.ItemTasksListBinding

class MyOrdersViewHolder(
    private val binding: ItemTasksListBinding
) : ViewHolder(
    binding.root
) {

    fun bind(model: ORDER) {
        binding.tvOrderCustomer.text = model.customer
        binding.tvOrderProduct.text = model.product_type
        binding.tvOrderDate.text = model.acceptance_date
    }

}