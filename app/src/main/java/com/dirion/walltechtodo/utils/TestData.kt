package com.dirion.walltechtodo.utils

import com.dirion.walltechtodo.view.features.notification.NotificationModel
import com.dirion.walltechtodo.view.features.notification.NotificationModel.SwitcherModel.SwitcherType
import com.dirion.walltechtodo.view.global.StatusTask

object TestData {





    val notificationModel = NotificationModel(
        notifications = listOf(
            NotificationModel.SwitcherModel(type = SwitcherType.READY, isChecked = false),
            NotificationModel.SwitcherModel(type = SwitcherType.STEADY, isChecked = false),
            NotificationModel.SwitcherModel(type = SwitcherType.GO, isChecked = false))
    )

}