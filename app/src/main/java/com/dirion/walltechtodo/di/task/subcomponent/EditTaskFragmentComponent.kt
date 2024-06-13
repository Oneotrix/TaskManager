package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.edit_order.EditOrderFragment
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent
interface EditTaskFragmentComponent {

    fun inject(fragment: EditOrderFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build() : EditTaskFragmentComponent

    }

}