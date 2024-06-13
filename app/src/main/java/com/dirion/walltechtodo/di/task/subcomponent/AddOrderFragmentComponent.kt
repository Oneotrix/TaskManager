package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.add_order.AddOrderFragment
import dagger.Subcomponent

@Subcomponent
interface AddOrderFragmentComponent {
    fun inject(fragment: AddOrderFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddOrderFragmentComponent
    }
}