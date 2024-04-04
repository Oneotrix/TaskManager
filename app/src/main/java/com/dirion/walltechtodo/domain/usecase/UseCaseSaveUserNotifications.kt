package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseSaveUserNotifications @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun execute(map: Map<String, Boolean>) {
        settingsRepository.saveUsersNotifications(map)
    }

}