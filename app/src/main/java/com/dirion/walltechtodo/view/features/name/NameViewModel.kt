package com.dirion.walltechtodo.view.features.name

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NameViewModel(
    private val useCaseWorkWithName: UseCaseWorkWithName
): ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data = _data.asStateFlow()


    init {
        setData()
    }

    fun saveNames() = useCaseWorkWithName.saveNames(
        firstName = _data.value.firstName,
        familyName = _data.value.familyName
    )


    fun updateFirstName(name: String) {
        _data.value = _data.value.copy(firstName = name)
    }

    fun updateFamilyName(name: String) {
        _data.value = _data.value.copy(familyName = name)
    }
    private fun setData() {
        val names = useCaseWorkWithName.getNames()
        val state = when(names == null) {
            true -> UiState()
            false -> UiState(firstName = names.first, familyName = names.second)
        }

        _data.value = state
    }

    data class UiState(
        val firstName: String = "",
        val familyName: String = "",
    )
}