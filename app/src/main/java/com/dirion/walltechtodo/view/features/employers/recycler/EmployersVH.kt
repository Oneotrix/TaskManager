package com.dirion.walltechtodo.view.features.employers.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.databinding.ItemTasksListBinding

class EmployersVH(
    private val binding: ItemTasksListBinding,
) : ViewHolder(binding.root){

    fun bind(model: USER) = with(binding){
        tvOrderCustomer.text = "${model.surname} ${model.name[0].uppercase()}. ${model.midle_name[0].uppercase()}."
        tvOrderProduct.text = model.login
        tvOrderDate.text = model.typName
    }

}