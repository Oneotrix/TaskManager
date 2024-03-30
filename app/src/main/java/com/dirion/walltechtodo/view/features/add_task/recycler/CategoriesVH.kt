package com.dirion.walltechtodo.view.features.add_task.recycler


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.databinding.CarouselItemBinding
import com.dirion.walltechtodo.view.global.StatusTask

class CategoriesVH(val binding: CarouselItemBinding): ViewHolder(binding.root) {

    fun bind(
        model: StatusVhModel,
        callback: (StatusTask) -> Unit
    )
    {
        binding.statusTitle.text = model.status.statusTitle
        binding.btnSwitch.isChecked = model.isChecked

        binding.btnSwitch.setOnClickListener {
            callback.invoke(model.status)
        }
    }


    fun bindSwitcher(isChecked: Boolean) {
        binding.btnSwitch.isChecked = isChecked
    }

}