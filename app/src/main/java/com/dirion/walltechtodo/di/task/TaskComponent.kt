package com.dirion.walltechtodo.di.task


import com.dirion.walltechtodo.di.task.module.TaskRepositoryModule
import com.dirion.walltechtodo.di.task.subcomponent.AddOrderFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.AddTypographyComponent
import com.dirion.walltechtodo.di.task.subcomponent.CustomersComponent
import com.dirion.walltechtodo.di.task.subcomponent.EditTaskFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.EmployersComponent
import com.dirion.walltechtodo.di.task.subcomponent.LkComponent
import com.dirion.walltechtodo.di.task.subcomponent.LoginFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.MyOrdersComponent
import com.dirion.walltechtodo.di.task.subcomponent.TasksFragmentComponent
import com.dirion.walltechtodo.di.task.subcomponent.TypographyComponent
import dagger.Subcomponent

@Subcomponent(
    modules = [
        TaskRepositoryModule::class,
        TaskSubcomponent::class
    ]
)
@TaskScope
interface TaskComponent {

    fun addOrderFragmentComponentBuilder() : AddOrderFragmentComponent.Builder
    fun editTaskFragmentComponentBuilder() : EditTaskFragmentComponent.Builder
    fun loginFragmentComponentBuilder() : LoginFragmentComponent.Builder
    fun tasksFragmentComponentBuilder() : TasksFragmentComponent.Builder
    fun typographyComponentBuilder() : TypographyComponent.Builder
    fun addTypographyComponentBuilder() : AddTypographyComponent.Builder
    fun lkComponentBuilder() : LkComponent.Builder
    fun customersComponentBuilder() : CustomersComponent.Builder

    fun employersComponentBuilder() : EmployersComponent.Builder

    fun myOrdersComponentBuilder() : MyOrdersComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun build() : TaskComponent
    }
}