package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.tasks.TasksFragment
import dagger.Subcomponent

@Subcomponent
interface TasksFragmentComponent {


    fun inject(fragment: TasksFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): TasksFragmentComponent
    }

}