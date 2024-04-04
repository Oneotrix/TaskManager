package com.dirion.walltechtodo.di.presentation

import com.dirion.walltechtodo.di.presentation.subcomponents.ActivityComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.AddTaskFragmentComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.EditTaskFragmentComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.LoginFragmentComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.NotificationFragmentComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.TasksFragmentComponent
import com.dirion.walltechtodo.di.scope.ScopePresentation
import dagger.Subcomponent

@Subcomponent
@ScopePresentation
interface PresentationComponent {

    fun activityComponentBuilder() : ActivityComponent.Builder

    fun tasksFragmentComponentBuilder(): TasksFragmentComponent.Builder

    fun loginFragmentComponentBuilder(): LoginFragmentComponent.Builder

    fun addTaskDialogBottomSheetBuilder() : AddTaskFragmentComponent.Builder

    fun editTaskDialogBottomSheetBuilder() : EditTaskFragmentComponent.Builder
    fun notificationFragmentComponentBuilder() : NotificationFragmentComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun build() : PresentationComponent
    }

}