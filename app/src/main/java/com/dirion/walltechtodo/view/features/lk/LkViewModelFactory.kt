package com.dirion.walltechtodo.view.features.lk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dirion.walltechtodo.domain.usecase.UseCaseGetAllUserInfo
import javax.inject.Inject

class LkViewModelFactory @Inject constructor(
    private val useCaseGetAllUserInfo: UseCaseGetAllUserInfo
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LkViewModel(
            userInfo = useCaseGetAllUserInfo
        ) as T
    }
}