package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseWorkWithName @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun saveNames(firstName: String, familyName: String) {
        settingsRepository.saveNames(firstName = firstName, familyName = familyName)
    }

    fun getNames() = settingsRepository.getNames()
}