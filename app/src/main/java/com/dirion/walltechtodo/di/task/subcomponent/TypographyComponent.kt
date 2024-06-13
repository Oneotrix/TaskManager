package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.typography.TypographyFragment
import dagger.Subcomponent

@Subcomponent
interface TypographyComponent {

    fun inject(fragment: TypographyFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build() : TypographyComponent

    }

}