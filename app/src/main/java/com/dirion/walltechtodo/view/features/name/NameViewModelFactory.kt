package com.dirion.walltechtodo.view.features.name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithName
import javax.inject.Inject

class NameViewModelFactory @Inject constructor(
    private val useCaseWorkWithName: UseCaseWorkWithName
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NameViewModel(useCaseWorkWithName) as T
    }
}