package com.dirion.walltechtodo

import android.app.Application
import android.util.DisplayMetrics
import com.dirion.walltechtodo.di.app.AppComponent
import com.dirion.walltechtodo.di.app.DaggerAppComponent
import com.dirion.walltechtodo.di.navgation.NavigationComponent
import com.dirion.walltechtodo.di.settings.SettingsComponent
import com.dirion.walltechtodo.di.task.TaskComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(this@App)

        taskComponent = appComponent.provideTaskComponentBuilder()
            .build()

        settingsComponent = appComponent.provideSettingsComponentBuilder()
            .build()

        navigationComponentFactory = appComponent.provideNavigationComponentFactory()

        displayMetrics = resources.displayMetrics
    }


    companion object {
        private lateinit var appComponent: AppComponent
        lateinit var taskComponent : TaskComponent
        lateinit var settingsComponent: SettingsComponent
        lateinit var navigationComponentFactory: NavigationComponent.Factory
        lateinit var displayMetrics: DisplayMetrics
    }
}