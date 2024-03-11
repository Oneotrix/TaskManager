package com.dirion.walltechtodo.di.presentation

import com.dirion.walltechtodo.di.presentation.subcomponents.ActivityComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.TasksFragmentComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.modules.PresentationSubcomponentModule
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.di.scope.ScopePresentation
import dagger.Subcomponent

@Subcomponent(
    modules = [PresentationSubcomponentModule::class]
)
@ScopePresentation
interface PresentationComponent {

    fun activityComponentBuilder() : ActivityComponent.Builder

    fun tasksFragmentComponentBuilder(): TasksFragmentComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun build() : PresentationComponent
    }

}