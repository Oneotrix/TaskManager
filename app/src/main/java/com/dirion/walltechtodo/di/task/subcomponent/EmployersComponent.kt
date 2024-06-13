package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.employers.EmployersFragment
import dagger.Subcomponent

@Subcomponent
interface EmployersComponent {

    fun inject(fragment: EmployersFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build() : EmployersComponent
    }
}