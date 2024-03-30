package com.dirion.walltechtodo

import android.app.Application
import android.util.DisplayMetrics
import com.dirion.walltechtodo.di.AppComponent
import com.dirion.walltechtodo.di.DaggerAppComponent
import com.dirion.walltechtodo.di.data.DataComponent
import com.dirion.walltechtodo.di.domain.DomainComponent
import com.dirion.walltechtodo.di.presentation.PresentationComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this@App)
            .build()

        presentationComponent = appComponent.presentationComponentBuilder()
            .build()

        dataComponent = appComponent.dataComponentBuilder()
            .build()

        domainComponent = appComponent.domainComponentBuilder()
            .build()


        displayMetrics = resources.displayMetrics
    }


    companion object {

        private lateinit var appComponent: AppComponent
        lateinit var presentationComponent: PresentationComponent
        lateinit var dataComponent: DataComponent
        lateinit var domainComponent: DomainComponent
        lateinit var displayMetrics: DisplayMetrics
    }
}