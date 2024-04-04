package com.dirion.walltechtodo.di

import android.content.Context
import com.dirion.walltechtodo.di.data.DataComponent
import com.dirion.walltechtodo.di.data.modules.ModuleRetrofit
import com.dirion.walltechtodo.di.data.modules.OkHttpModule
import com.dirion.walltechtodo.di.data.modules.RoomModule
import com.dirion.walltechtodo.di.data.modules.SharedPreferencesModule
import com.dirion.walltechtodo.di.domain.DomainComponent
import com.dirion.walltechtodo.di.domain.module.SettingsRepositoryModule
import com.dirion.walltechtodo.di.domain.module.TasksRepositoryModule
import com.dirion.walltechtodo.di.presentation.PresentationComponent
import com.dirion.walltechtodo.di.scope.ScopeApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ScopeApplication
@Component(
    modules = [
        AppSubcomponents::class,
        TasksRepositoryModule::class,
        OkHttpModule::class,
        ModuleRetrofit::class,
        RoomModule::class,
        SharedPreferencesModule::class,
        SettingsRepositoryModule::class,
    ],
)
interface AppComponent {

    fun dataComponentBuilder() : DataComponent.Builder

    fun presentationComponentBuilder() : PresentationComponent.Builder

    fun domainComponentBuilder() : DomainComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context) : Builder

        fun build(): AppComponent
    }
}