package com.dirion.walltechtodo.di.app

import com.dirion.walltechtodo.di.navgation.NavigationComponent
import com.dirion.walltechtodo.di.settings.SettingsComponent
import com.dirion.walltechtodo.di.task.TaskComponent
import dagger.Module


@Module(subcomponents = [
    TaskComponent::class,
    SettingsComponent::class,
    NavigationComponent::class,
])
class AppSubcomponents