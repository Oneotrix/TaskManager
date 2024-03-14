package com.dirion.walltechtodo.di.domain

import com.dirion.walltechtodo.di.domain.module.TasksRepositoryModule
import com.dirion.walltechtodo.domain.repository.TasksRepository
import dagger.Binds
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