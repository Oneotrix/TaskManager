package com.dirion.walltechtodo.view.features.add_typography

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.domain.usecase.UseCaseAddTypography
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTypographyViewModel(
    private val useCaseAddTypography: UseCaseAddTypography
): ViewModel() {

    private val _data = MutableStateFlow(UiState.State())
    val data = _data.asStateFlow()


    fun addTypography() = viewModelScope.launch {
        useCaseAddTypography(
            TIPOGRAPHY(
                hood = _data.value.hood,
                name = _data.value.name,
                phone = _data.value.phone,
                type = _data.value.type.toString(),
                year = _data.value.year
            )
        )
    }

    fun updateHood(hood: String) {
        _data.update {
            it.copy(
                hood = hood
            )
        }
    }

    fun updateName(name: String) {
        _data.update {
            it.copy(
                name = name
            )
        }
    }

    fun updatePhone(phone: String) {
        _data.update {
            it.copy(
                phone = phone
            )
        }
    }

    fun updateType(type: Int) {
        _data.update {
            it.copy(
                type = type
            )
        }
    }

    fun updateYear(year: String) {
        _data.update {
            it.copy(
                year = year
            )
        }
    }

    sealed class UiState {
        //1 - ИП
        //2 - ООО
        data class State(
            val hood: String = "",
            val name: String = "",
            val phone: String = "",
            val type: Int = 1,
            val year: String = ""
        )

    }
}