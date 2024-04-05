package com.dirion.walltechtodo.view.features.notification.recycler

import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.ItemNotificationListBinding
import com.dirion.walltechtodo.view.features.notification.NotificationModel
import com.dirion.walltechtodo.view.features.notification.NotificationModel.SwitcherModel

class NotificationViewHolder(
    private val binding: ItemNotificationListBinding,
    private val callbackUpdateModel: (SwitcherModel) -> Unit
) : ViewHolder(binding.root) {

    fun bind(model: SwitcherModel) {
        binding.tvNotificationTitle.text = SwitcherModel.SwitcherType.titleByType(model.type)
        binding.btnSwitchNotification.isChecked = model.isChecked

        setThumbColor()

        if (binding.btnSwitchNotification.isChecked) {
            setActiveTrackTintColor(model.type)
        } else {
            setDefaultTrackTintColor()
        }

        setOnSwitchListener(model)

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

    private fun setOnSwitchListener(model: SwitcherModel) {
        val type = model.type

        binding.btnSwitchNotification.setOnCheckedChangeListener { _, b ->
            callbackUpdateModel(model.copy(isChecked = b))
            if (b) {
                setActiveTrackTintColor(type)
            } else {
                setDefaultTrackTintColor()
            }
        }
    }

}