package com.dirion.walltechtodo.view.features.volume

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithVolume
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VolumeViewModel(
    private val useCaseWorkWithVolume: UseCaseWorkWithVolume
) : ViewModel() {

    private val _data = MutableStateFlow(0f)
    val data = _data.asStateFlow()

    init {
        setData()
    }

    fun updateValue(value: Float) {
        _data.value = value
    }

    fun saveValue() {
        useCaseWorkWithVolume.save(_data.value)
    }

    private fun setData() {
        _data.value = useCaseWorkWithVolume.get()
    }

}