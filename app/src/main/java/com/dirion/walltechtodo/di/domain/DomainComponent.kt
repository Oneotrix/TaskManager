package com.dirion.walltechtodo.di.domain

import com.dirion.walltechtodo.di.domain.module.TasksRepositoryModule
import dagger.Subcomponent

@Subcomponent(
    modules = [TasksRepositoryModule::class]
)
interface DomainComponent {


    @Subcomponent.Builder
    interface Builder {

        fun build() : DomainComponent

    }

}