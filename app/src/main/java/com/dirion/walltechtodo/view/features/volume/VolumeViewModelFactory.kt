package com.dirion.walltechtodo.view.features.volume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithVolume
import javax.inject.Inject

class VolumeViewModelFactory @Inject constructor(
    private val useCaseWorkWithVolume: UseCaseWorkWithVolume
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VolumeViewModel(useCaseWorkWithVolume) as T
    }
}