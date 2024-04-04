package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.notification.NotificationsFragment
import dagger.Subcomponent

@Subcomponent
interface NotificationFragmentComponent {

    fun inject(fragment: NotificationsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): NotificationFragmentComponent
    }
}