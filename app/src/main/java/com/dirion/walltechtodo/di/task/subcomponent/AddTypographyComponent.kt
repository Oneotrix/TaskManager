package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.add_typography.AddTypographyFragment
import dagger.Subcomponent

@Subcomponent
interface AddTypographyComponent {

    fun inject(fragment: AddTypographyFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build() : AddTypographyComponent

    }

}