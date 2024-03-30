package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.add_task.AddTaskFragment
import dagger.Subcomponent

@Subcomponent
interface AddTaskFragmentComponent {
    fun inject(fragment: AddTaskFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AddTaskFragmentComponent
    }
}