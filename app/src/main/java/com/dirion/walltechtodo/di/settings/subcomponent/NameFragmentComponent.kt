package com.dirion.walltechtodo.di.settings.subcomponent

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