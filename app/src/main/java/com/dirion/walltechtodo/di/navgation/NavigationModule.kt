package com.dirion.walltechtodo.di.navgation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {
    @Provides
    fun navigation(
        activity: MainActivity
    ): NavController {
        return Navigation.findNavController(activity, R.id.fragmentContainer)
    }
}