package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseWorkWithTime @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun saveTime(time: Pair<Int, Int>) = settingsRepository.saveTime(time)

    fun getTime() = settingsRepository.getTime()

}