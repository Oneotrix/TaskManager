package com.dirion.walltechtodo.di.settings.subcomponent

import com.dirion.walltechtodo.view.features.volume.VolumeFragment
import dagger.Subcomponent

@Subcomponent
interface VolumeFragmentComponent {

    fun inject(fragment: VolumeFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): VolumeFragmentComponent
    }
}