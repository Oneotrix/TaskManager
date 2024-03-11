package com.dirion.walltechtodo.di.domain

import com.dirion.walltechtodo.di.domain.module.UseCaseModule
import com.dirion.walltechtodo.domain.usecase.GetTasksUseCase
import dagger.Subcomponent

@Subcomponent(
    modules =[
        UseCaseModule::class,
    ]
)
interface DomainComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build() : DomainComponent

    }

}