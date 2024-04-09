package com.dirion.walltechtodo.di.navgation

import androidx.navigation.NavController
import com.dirion.walltechtodo.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NavigationModule::class,
    ]
)
interface NavigationComponent {

    fun navigationController(): NavController
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: MainActivity
        ) : NavigationComponent
    }

}