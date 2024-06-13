package com.dirion.walltechtodo.di.task

import com.dirion.walltechtodo.di.task.subcomponent.AddOrderFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.EditTaskFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.LoginFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.TasksFragmentComponent
import dagger.Module

@Module(subcomponents = [
    AddOrderFragmentComponent::class,
    EditTaskFragmentComponent::class,
    LoginFragmentComponent::class,
    TasksFragmentComponent::class,

])
class TaskSubcomponent