package com.dirion.walltechtodo.di.settings

import com.dirion.walltechtodo.di.settings.module.SettingsRepositoryModule
import com.dirion.walltechtodo.di.settings.subcomponent.DateTimeFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.NameFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.NotesFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.NotificationFragmentComponent
import com.dirion.walltechtodo.di.settings.subcomponent.VolumeFragmentComponent
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SettingsRepositoryModule::class,
        SettingsSubcomponent::class
    ]
)
@SettingsScope
interface SettingsComponent {

    fun dateTimeFragmentComponentBuilder() : DateTimeFragmentComponent.Builder
    fun nameFragmentComponentBuilder() : NameFragmentComponent.Builder
    fun notesFragmentComponentBuilder() : NotesFragmentComponent.Builder
    fun notificationFragmentComponentBuilder() : NotificationFragmentComponent.Builder
    fun volumeFragmentComponentBuilder() : VolumeFragmentComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun build() : SettingsComponent
    }
}