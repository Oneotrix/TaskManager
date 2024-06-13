package com.dirion.walltechtodo.view.features.customers.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.data.models.network.rest.CLIENT
import com.dirion.walltechtodo.databinding.ItemTasksListBinding

class CustomerVH(
    private val binding: ItemTasksListBinding
): ViewHolder(
    binding.root
) {

    fun bind(model: CLIENT) = with(binding) {
        tvOrderCustomer.text = "${model.surname} ${model.name[0].uppercase()}. ${model.middlename[0].uppercase()}."
        tvOrderProduct.text = model.account
        tvOrderDate.text = model.phone
    }



}