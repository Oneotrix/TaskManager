package com.dirion.walltechtodo.di.presentation.subcomponents

import androidx.navigation.NavController
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.di.presentation.modules.NavigationModule
import com.dirion.walltechtodo.di.presentation.subcomponents.modules.ActivitySubcomponentsModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NavigationModule::class,
        ActivitySubcomponentsModule::class,
    ]
)
interface ActivityComponent {

    fun navigationController(): NavController

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MainActivity): Builder
        fun build(): ActivityComponent
    }
}