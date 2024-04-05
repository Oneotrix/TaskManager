package com.dirion.walltechtodo.view.features.notification.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dirion.walltechtodo.databinding.ItemNotificationListBinding
import com.dirion.walltechtodo.utils.Comparator
import com.dirion.walltechtodo.view.features.notification.NotificationModel

class AdapterNotification(
    private val callbackUpdateModel: (NotificationModel.SwitcherModel) -> Unit
):
    ListAdapter<NotificationModel.SwitcherModel, NotificationViewHolder>(Comparator.notificationDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            binding = ItemNotificationListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callbackUpdateModel
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}