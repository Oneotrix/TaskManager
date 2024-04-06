package com.dirion.walltechtodo.domain.usecase

import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import javax.inject.Inject

class UseCaseWorkWithNotes @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {

    fun saveNotes(notes: String) = settingsRepository.saveNotes(notes)

    fun getNotes() = settingsRepository.getNotes()
}