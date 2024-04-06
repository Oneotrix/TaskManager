package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseWorkWithVolume @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun save(value: Float) = settingsRepository.saveVolumeValue(value)

    fun get() = settingsRepository.getVolumeValue()

}