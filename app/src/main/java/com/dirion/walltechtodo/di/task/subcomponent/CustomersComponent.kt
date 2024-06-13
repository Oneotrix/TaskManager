package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.customers.CustomersFragment
import dagger.Subcomponent

@Subcomponent
interface CustomersComponent {

    fun inject(fragment: CustomersFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build() : CustomersComponent
    }

}