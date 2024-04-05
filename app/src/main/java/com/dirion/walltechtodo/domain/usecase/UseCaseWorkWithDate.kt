package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseWorkWithDate @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun saveDate(timestamp: Long) = settingsRepository.saveDateTimestamp(timestamp)

    fun getDate() = settingsRepository.getDateTimestamp()

}