package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseGetUserNotifications @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun getNotifications() = settingsRepository.getUsersNotifications()
}