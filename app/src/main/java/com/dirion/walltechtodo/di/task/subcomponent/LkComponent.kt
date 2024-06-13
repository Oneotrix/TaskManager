package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.lk.LkFragment
import dagger.Subcomponent

@Subcomponent
interface LkComponent {

    fun inject(fragment: LkFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): LkComponent
    }
}