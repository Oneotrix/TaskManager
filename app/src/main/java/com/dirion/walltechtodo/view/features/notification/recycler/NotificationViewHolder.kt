package com.dirion.walltechtodo.view.features.notification.recycler

import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.ItemNotificationListBinding
import com.dirion.walltechtodo.view.features.notification.NotificationModel.SwitcherModel

class NotificationViewHolder(
    private val binding: ItemNotificationListBinding
) : ViewHolder(binding.root) {

    fun bind(model: SwitcherModel) {
        binding.tvNotificationTitle.text = SwitcherModel.SwitcherType.typeTitle(model.type)
        binding.btnSwitchNotification.isChecked = model.isChecked

        setThumbColor()

        if (binding.btnSwitchNotification.isChecked) {
            setActiveTrackTintColor(model.type)
        } else {
            setDefaultTrackTintColor()
        }

        setOnSwitchListener(model.type)

    }

    private fun setDefaultTrackTintColor() {
        val  color = binding.root.context.getColor(R.color.grey)
        binding.btnSwitchNotification.trackTintList = ColorStateList.valueOf(color)
    }

    private fun setThumbColor() {
        val colorDefault = binding.root.context.getColor(R.color.white)
        binding.btnSwitchNotification.thumbTintList = ColorStateList.valueOf(colorDefault)
    }

    private fun setActiveTrackTintColor(type: SwitcherModel.SwitcherType) {
        val  color = binding.root.context.getColor(SwitcherModel.SwitcherType.activeTintTrackColor(type))
        binding.btnSwitchNotification.trackTintList = ColorStateList.valueOf(color)
    }

    private fun setOnSwitchListener(type: SwitcherModel.SwitcherType) {
        binding.btnSwitchNotification.setOnCheckedChangeListener { _, b ->
            if (b) {
                setActiveTrackTintColor(type)
            } else {
                setDefaultTrackTintColor()
            }
        }
    }

}