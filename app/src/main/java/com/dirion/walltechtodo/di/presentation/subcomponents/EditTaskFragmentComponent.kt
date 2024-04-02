package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.edit_task.EditTaskFragment
import dagger.Subcomponent

@Subcomponent
interface EditTaskFragmentComponent {

    fun inject(fragment: EditTaskFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build() : EditTaskFragmentComponent

    }

}