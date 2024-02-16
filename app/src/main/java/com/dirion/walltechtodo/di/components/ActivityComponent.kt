package com.dirion.walltechtodo.di.components

import androidx.navigation.NavController
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.di.modules.NavigationModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [NavigationModule::class]
)
interface ActivityComponent {

    fun navigationController(): NavController

   // fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MainActivity): Builder
        fun build(): ActivityComponent
    }
}