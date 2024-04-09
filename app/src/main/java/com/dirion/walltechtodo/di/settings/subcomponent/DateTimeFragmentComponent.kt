package com.dirion.walltechtodo.di.settings.subcomponent

import com.dirion.walltechtodo.view.features.data_time.DateTimeFragment
import dagger.Subcomponent

@Subcomponent
interface DateTimeFragmentComponent {

    fun inject(frag: DateTimeFragment)
    @Subcomponent.Builder
    interface Builder {

        fun build() : DateTimeFragmentComponent

    }

}