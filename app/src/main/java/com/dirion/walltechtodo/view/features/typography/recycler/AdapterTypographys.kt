package com.dirion.walltechtodo.view.features.typography.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.databinding.ItemTypographyBinding
import com.dirion.walltechtodo.utils.Comparator

class AdapterTypographys(
    private val callbackDeleteTask: (String) -> Unit,
    private val callbackEditTask: (String) -> Unit,
) : ListAdapter<TIPOGRAPHY, TypographyVH >(
    Comparator.typographyDU
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypographyVH {
        return TypographyVH(
            binding = ItemTypographyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callbackEditTask = callbackEditTask,

        )
    }

    override fun onBindViewHolder(holder: TypographyVH, position: Int) {
        holder.bind(getItem(position), callbackDeleteTask)
    }
}