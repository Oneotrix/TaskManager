package com.dirion.walltechtodo.view.features.add_typography

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseAddTypography
import javax.inject.Inject

class AddTypographyViewModelFactory @Inject constructor(
    private val useCaseAddTypography: UseCaseAddTypography
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTypographyViewModel(
            useCaseAddTypography = useCaseAddTypography
        ) as T
    }
}