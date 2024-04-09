package com.dirion.walltechtodo.di.task


import com.dirion.walltechtodo.di.task.module.TaskRepositoryModule
import com.dirion.walltechtodo.di.task.subcomponent.AddTaskFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.EditTaskFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.LoginFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.TasksFragmentComponent
import dagger.Subcomponent

@Subcomponent(
    modules = [
        TaskRepositoryModule::class,
        TaskSubcomponent::class
    ]
)
@TaskScope
interface TaskComponent {

    fun addTaskFragmentComponentBuilder() : AddTaskFragmentComponent.Builder
    fun editTaskFragmentComponentBuilder() : EditTaskFragmentComponent.Builder
    fun loginFragmentComponentBuilder() : LoginFragmentComponent.Builder
    fun tasksFragmentComponentBuilder() : TasksFragmentComponent.Builder
    @Subcomponent.Builder
    interface Builder {
        fun build() : TaskComponent
    }
}