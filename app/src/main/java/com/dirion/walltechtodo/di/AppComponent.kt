package com.dirion.walltechtodo.di

import com.dirion.walltechtodo.di.data.DataComponent
import com.dirion.walltechtodo.di.data.modules.OkHttpModule
import com.dirion.walltechtodo.di.domain.DomainComponent
import com.dirion.walltechtodo.di.domain.module.TasksRepositoryModule
import com.dirion.walltechtodo.di.presentation.PresentationComponent
import com.dirion.walltechtodo.di.scope.ScopeApplication
import dagger.Component

@ScopeApplication
@Component(
    modules = [
        AppSubcomponents::class,
        TasksRepositoryModule::class,
        OkHttpModule::class,
    ],
)
interface AppComponent {

    fun dataComponentBuilder() : DataComponent.Builder

    fun presentationComponentBuilder() : PresentationComponent.Builder

    fun domainComponentBuilder() : DomainComponent.Builder

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}