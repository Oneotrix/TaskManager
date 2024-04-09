package com.dirion.walltechtodo.di.app

import android.content.Context
import com.dirion.walltechtodo.di.app.modules.LocalModule
import com.dirion.walltechtodo.di.app.modules.NetworkModule
import com.dirion.walltechtodo.di.navgation.NavigationComponent
import com.dirion.walltechtodo.di.settings.SettingsComponent
import com.dirion.walltechtodo.di.task.TaskComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        NetworkModule::class,
        LocalModule::class,
        AppSubcomponents::class
    ]
)
@Singleton
interface AppComponent {

    fun provideTaskComponentBuilder() : TaskComponent.Builder

    fun provideSettingsComponentBuilder() : SettingsComponent.Builder

    fun provideNavigationComponentFactory() : NavigationComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ) : AppComponent
    }
}