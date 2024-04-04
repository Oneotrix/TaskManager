package com.dirion.walltechtodo.view.features.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseGetUserNotifications
import com.dirion.walltechtodo.domain.usecase.UseCaseSaveUserNotifications
import javax.inject.Inject

class NotificationViewModelFactory @Inject constructor(
    private val useCaseSaveUserNotifications: UseCaseSaveUserNotifications,
    private val useCaseGetUserNotifications: UseCaseGetUserNotifications
): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationViewModel(
            useCaseSaveUserNotifications = useCaseSaveUserNotifications,
            useCaseGetUserNotifications = useCaseGetUserNotifications
        ) as T
    }
}