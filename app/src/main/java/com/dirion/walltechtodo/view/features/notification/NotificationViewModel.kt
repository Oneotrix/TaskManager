package com.dirion.walltechtodo.view.features.notification

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.usecase.UseCaseGetUserNotifications
import com.dirion.walltechtodo.domain.usecase.UseCaseSaveUserNotifications
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.features.notification.NotificationModel.SwitcherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel(
    private val useCaseSaveUserNotifications: UseCaseSaveUserNotifications,
    private val useCaseGetUserNotifications: UseCaseGetUserNotifications
) : ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data = _data.asStateFlow()

    fun setUi() {
        val map: MutableMap<String, Boolean> = mutableMapOf()

        TestData.notificationModel.notifications.forEach {
            val key = SwitcherModel.SwitcherType.typeTitle(it.type)
            val value = it.isChecked
            map[key] = value
        }

        useCaseSaveUserNotifications.execute(map)
    }



    data class UiState(
        val model: NotificationModel = TestData.notificationModel
    )
}