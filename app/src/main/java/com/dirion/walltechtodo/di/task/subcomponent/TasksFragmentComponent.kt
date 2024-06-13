package com.dirion.walltechtodo.di.task.subcomponent

import com.dirion.walltechtodo.view.features.orders.OrdersFragment
import dagger.Subcomponent

@Subcomponent
interface TasksFragmentComponent {


    fun inject(fragment: OrdersFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): TasksFragmentComponent
    }

}