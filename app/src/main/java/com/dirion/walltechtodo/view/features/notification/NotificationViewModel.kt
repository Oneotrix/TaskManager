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

    init {
        initData()
    }

    private fun initData()  {
        val switcherState = useCaseGetUserNotifications.getNotifications()

        var model = TestData.notificationModel

        if (switcherState.isNotEmpty()) {
            val switcherList: MutableList<SwitcherModel> = mutableListOf()

            switcherState.forEach {
                val type = SwitcherModel.SwitcherType.typeByTitle(it.key)
                val isChecked = it.value

                switcherList.add(SwitcherModel(type = type, isChecked = isChecked))
            }

            model = NotificationModel(switcherList)
        }

        _data.value = _data.value.copy(model = model)
    }

    fun updateSwitcherModel(model: SwitcherModel) {
        val list = _data.value.model.notifications.toMutableList()

        for (i in list.indices) {
            if (list[i].type == model.type) list[i] = model
        }

        _data.value = _data.value.copy(model = NotificationModel(list))
    }

    fun saveData() {
        val map: MutableMap<String, Boolean> = mutableMapOf()

        _data.value.model.notifications.forEach {
            val key = SwitcherModel.SwitcherType.titleByType(it.type)
            val value = it.isChecked
            map[key] = value
        }

        useCaseSaveUserNotifications.execute(map)
    }

    data class UiState(
        val model: NotificationModel = TestData.notificationModel
    )
}