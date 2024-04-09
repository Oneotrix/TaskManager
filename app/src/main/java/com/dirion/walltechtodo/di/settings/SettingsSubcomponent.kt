package com.dirion.walltechtodo.di.settings

import com.dirion.walltechtodo.di.settings.subcomponent.DateTimeFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.NameFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.NotesFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.NotificationFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.VolumeFragmentComponent
import dagger.Module

@Module(subcomponents = [
    DateTimeFragmentComponent::class,
    NameFragmentComponent::class,
    NotesFragmentComponent::class,
    NotificationFragmentComponent::class,
    VolumeFragmentComponent::class,
])
class SettingsSubcomponent