package com.dirion.walltechtodo

import android.app.Application
import com.dirion.walltechtodo.di.AppComponent
import com.dirion.walltechtodo.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .build()
    }


    companion object {

        lateinit var appComponent: AppComponent

    }
}