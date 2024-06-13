package com.dirion.walltechtodo.view.features.typography

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.domain.usecase.UseCaseDeleteTypography
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllTypography
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TypographyViewModel(
    private val useCaseGetAllTypography : UseCaseGetAllTypography,
    private val useCaseDeleteTypography: UseCaseDeleteTypography
) : ViewModel() {

    private val _data = MutableStateFlow(listOf<TIPOGRAPHY>())
    val data = _data.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() = viewModelScope.launch {
        _data.update {
            val list = useCaseGetAllTypography.invoke()
            Log.d("TypographyViewModel", list.toString())
            list
        }
    }

    fun deleteTypography(id: String) = viewModelScope.launch {
        _data.update { list ->
            list.filter {
                it.id != id
            }
        }

        useCaseDeleteTypography(id)
    }
}