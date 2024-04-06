package com.dirion.walltechtodo.view.features.notes

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithNotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotesViewModel(private val useCaseWorkWithNotes: UseCaseWorkWithNotes) : ViewModel() {

    private val _data = MutableStateFlow<String>("")
    val data = _data.asStateFlow()


    init {
        setData()
    }

    private fun setData() {
        val notes = useCaseWorkWithNotes.getNotes()

        _data.value = when(notes.isNotBlank()) {
            true -> notes
            false -> ""
        }
    }

    fun updateData(notes: String) {
        _data.value = notes
        saveNotes()
    }

    private fun saveNotes() {
        useCaseWorkWithNotes.saveNotes(_data.value)
    }


}