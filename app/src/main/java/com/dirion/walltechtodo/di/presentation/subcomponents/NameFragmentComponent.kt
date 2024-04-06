package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.name.NameFragment
import dagger.Subcomponent

@Subcomponent
interface NameFragmentComponent {

    fun inject(fragment: NameFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): NameFragmentComponent
    }
}