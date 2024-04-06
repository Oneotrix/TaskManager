package com.dirion.walltechtodo.di.presentation.subcomponents

import com.dirion.walltechtodo.view.features.notes.NotesFragment
import dagger.Subcomponent

@Subcomponent
interface NotesFragmentComponent {

    fun inject(fragment: NotesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): NotesFragmentComponent
    }
}