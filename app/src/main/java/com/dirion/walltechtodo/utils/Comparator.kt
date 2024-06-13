package com.dirion.walltechtodo.utils

import androidx.recyclerview.widget.DiffUtil
import com.dirion.walltechtodo.data.models.network.rest.CLIENT
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.view.features.notification.NotificationModel
import com.dirion.walltechtodo.view.features.orders.OrderModel

object Comparator {

    val ordersListDiffUtil = object: DiffUtil.ItemCallback<OrderModel>() {
        override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean =
            oldItem.selectCustomer == newItem.selectCustomer &&
            oldItem.product_type == newItem.product_type &&
            oldItem.acceptance_date == newItem.acceptance_date
    }



    val notificationDiffUtil = object: DiffUtil.ItemCallback<NotificationModel.SwitcherModel>() {
        override fun areItemsTheSame(
            oldItem: NotificationModel.SwitcherModel,
            newItem: NotificationModel.SwitcherModel
        ): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(
            oldItem: NotificationModel.SwitcherModel,
            newItem: NotificationModel.SwitcherModel
        ): Boolean {
            return oldItem.isChecked == newItem.isChecked
        }


    }

    val typographyDU = object : DiffUtil.ItemCallback<TIPOGRAPHY>() {
        override fun areItemsTheSame(oldItem: TIPOGRAPHY, newItem: TIPOGRAPHY): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: TIPOGRAPHY, newItem: TIPOGRAPHY): Boolean {
            return true
        }


    }

    val customersDU = object : DiffUtil.ItemCallback<CLIENT>() {
        override fun areItemsTheSame(oldItem: CLIENT, newItem: CLIENT): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CLIENT, newItem: CLIENT): Boolean {
            return  oldItem == newItem
        }

    }

    val employersDU = object : DiffUtil.ItemCallback<USER>() {
        override fun areItemsTheSame(oldItem: USER, newItem: USER): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: USER, newItem: USER): Boolean {
            return oldItem == newItem
        }

    }

    val orderDU = object : DiffUtil.ItemCallback<ORDER>() {
        override fun areItemsTheSame(oldItem: ORDER, newItem: ORDER): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: ORDER, newItem: ORDER): Boolean {
            return true
        }

    }


    sealed interface AddTaskPayload {
        data class Switcher(val isChecked: Boolean) : AddTaskPayload
    }
}