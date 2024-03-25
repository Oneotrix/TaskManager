package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.login.LoginFragment
import dagger.Subcomponent

@Subcomponent
interface LoginFragmentComponent {
    fun inject(fragment: LoginFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): LoginFragmentComponent
    }
}