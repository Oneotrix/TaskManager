package com.dirion.walltechtodo.view.features.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithNotes
import javax.inject.Inject

class NotesViewModelFactory @Inject constructor(
    private val useCaseWorkWithNotes: UseCaseWorkWithNotes,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(
            useCaseWorkWithNotes = useCaseWorkWithNotes,
        ) as T
    }
}