package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.my_orders.MyOrdersFragment
import dagger.Subcomponent

@Subcomponent
interface MyOrdersComponent {

    fun inject(fragment: MyOrdersFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build() : MyOrdersComponent
    }
}