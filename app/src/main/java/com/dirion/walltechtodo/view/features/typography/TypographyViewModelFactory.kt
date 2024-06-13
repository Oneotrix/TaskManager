package com.dirion.walltechtodo.view.features.typography

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseDeleteTypography
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllTypography
import javax.inject.Inject

class TypographyViewModelFactory @Inject constructor(
    private val useCaseGetAllTypography : UseCaseGetAllTypography,
    private val useCaseDeleteTypography: UseCaseDeleteTypography
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TypographyViewModel(
            useCaseGetAllTypography,
            useCaseDeleteTypography
        ) as T
    }

}